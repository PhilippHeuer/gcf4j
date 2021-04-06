package com.github.philippheuer.gcf4j.core.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.command.ICommandManager;
import com.github.philippheuer.gcf4j.api.command.IGCFCommand;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.mock.MockTracer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A registry for all registered commands
 */
@Slf4j
public class CommandManager implements ICommandManager {

    private Tracer tracer;

    // holds all registered commands
    private final List<IGCFCommand> commandList = new ArrayList<>();

    // holds a lookup map to find the command based on the name or a alias
    private final Map<String, IGCFCommand> commandMap = new CaseInsensitiveMap<>();

    // holds global execution limiters
    @Getter
    private Set<IExecutionLimiter> defaultLimiters = new HashSet<>();

    /**
     * command manager
     */
    public CommandManager() {
        this.tracer = new MockTracer();
    }

    /**
     * command manager
     *
     * @param tracer OpenTracing Tracer
     */
    public CommandManager(Tracer tracer) {
        this.tracer = tracer;

        if (this.tracer == null) {
            this.tracer = new MockTracer();
        }
    }

    /**
     * registers a command
     *
     * @param command The command to register.
     * @return returns true if the command was registered successfully
     */
    public boolean register(IGCFCommand command) {
        if (commandMap.containsKey(command.getName()))
            return false;

        // add to command list
        commandList.add(command);

        // add to lookup map
        commandMap.put(command.getName(), command);
        if (command.getAliases() != null) {
            command.getAliases().forEach(alias -> commandMap.put(alias, command));
        }

        log.info("Registered command {} [Aliases: {}]", command.getName(), command.getAliases());
        return true;
    }

    /**
     * @return a unmodifiable list of all commands
     */
    public List<? extends IGCFCommand> getCommands() {
        return Collections.unmodifiableList(commandList);
    }

    /**
     * optionally returns the command associated with the given name;
     *
     * @param name The name to look up.
     * @return The command associated with the given name.
     */
    public Optional<IGCFCommand> getCommand(String name) {
        return Optional.ofNullable(commandMap.get(name));
    }

    /**
     * is this message a command?
     *
     * @param ctx the message context
     * @return true if the message contains a existing command regardless of permissions
     */
    public boolean isCommand(IGCFMessageContext ctx) {
        if (ctx.getMessage().getCommand() != null) {
            return getCommand(ctx.getMessage().getCommand()).isPresent();
        }

        return false;
    }

    /**
     * has permissions to run the command?
     *
     * @param ctx the message context
     * @return true if the user has permissions to run the command
     */
    public boolean hasPermissions(IGCFMessageContext ctx) {
        if (ctx.getMessage().getCommand() == null)
            return false;

        return hasPermissions(ctx, ctx.getMessage().getCommand());
    }

    /**
     * has permissions to run the command?
     *
     * @param ctx the message context
     * @param commandName name of the target command
     * @return true if the user has permissions to run the command
     */
    public boolean hasPermissions(IGCFMessageContext ctx, String commandName) {
        // global limiters
        if (defaultLimiters != null) {
            for (IExecutionLimiter limiter : defaultLimiters) {
                if (!limiter.check(ctx)) {
                    limiter.onFail(ctx);
                    return false;
                }
            }
        }

        // command limiters
        var cmdOpt = getCommand(commandName);
        if (cmdOpt.isEmpty())
            return false;

        var cmd = cmdOpt.get();
        if (cmd.getCommandLimiters() != null) {
            for (IExecutionLimiter limiter : cmd.getCommandLimiters()) {
                if (!limiter.check(ctx)) {
                    limiter.onFail(ctx);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Parses the message context and returns a message that contains the command / command arguments
     *
     * @param ctx the message context
     * @return true if the message contains a command, false otherwise
     */
    public boolean parseMessage(IGCFMessageContext ctx, Set<String> triggers) {
        ctx.getSpan().log(Map.of("event", "message.check.trigger", "triggers", triggers));

        // check message for all available triggers
        var text = ctx.getMessage().getText();
        for (var trigger : triggers) {
            if (text.startsWith(trigger)) {
                log.debug("[{}] message [{}] may be a command. [Trigger:{}]", ctx.getInstance().getKey(), ctx.getMessage().getText(), trigger);
                ctx.getSpan().log(Map.of("event", "message.has.commandprefix", "trigger", trigger));

                String messageWithoutPrefix = text.substring(trigger.length());
                List<String> args = Arrays.asList(messageWithoutPrefix.split("\\s", 2));

                ctx.getMessage().setCommand(args.get(0));
                ctx.getMessage().setCommandPayload(args.size() > 1 ? args.get(1) : null);

                return true;
            }
        }

        return false;
    }

    /**
     * Runs the command, does not check permissions or that the command is valid.
     * <p>
     * Please handle CommandManager#isCommand and CommandManager#hasPermissions accordingly.
     *
     * @param ctx the message context, parseMessage needs to be called on the ctx before calling this
     */
    public IGCFMessageContext runCommand(IGCFMessageContext ctx) {
        Span span = tracer.buildSpan("message.command.handle").asChildOf(ctx.getSpan()).start();
        IGCFMessageContext response = null;

        // ignore bot messages
        if (ctx.getAuthor().isBot()) {
            span.log(Map.of("event", "skip.author.bot"));
            span.finish();
            return null;
        }

        // get text
        var text = ctx.getMessage().getText();
        if (StringUtils.isEmpty(text)) {
            span.log(Map.of("event", "skip.text.empty"));
            span.finish();
            return null;
        }

        try {
            var cmd = getCommand(ctx.getMessage().getCommand()).get();
            response = cmd.onSuperExecution(ctx);
        } catch (Exception ex) {
            // can happen if the command can't be found, but shouldn't happen
            log.debug("unexpected error, aborting command execution! {}", ex.getMessage(), ex);
        } finally {
            span.finish();
        }

        return response;
    }
}
