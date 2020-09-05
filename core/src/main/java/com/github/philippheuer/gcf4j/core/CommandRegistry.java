package com.github.philippheuer.gcf4j.core;

import com.github.philippheuer.gcf4j.api.ICommandRegistry;
import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.core.domain.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@NoArgsConstructor
public class CommandRegistry implements ICommandRegistry {

    // holds all registered commands
    private final Map<String, Command> commands = new HashMap<>();

    // holds the default execution limiters
    @Getter
    private Set<IExecutionLimiter> defaultLimiters;

    /**
     * Constructs a new CommandRegistry with a few global limiters
     *
     * @param defaultLimiters
     */
    public CommandRegistry(Set<IExecutionLimiter> defaultLimiters) {
        this.defaultLimiters = defaultLimiters;
    }

    /**
     * {@inheritDoc }
     */
    public void call(IGCFMessageContext ctx) {
        if (ctx.getMessage().getCommand() != null) {
            var cmd = getCommand(ctx.getMessage().getCommand());
            if (cmd.isPresent()) {
                call(cmd.get(), ctx);
            } else {
                log.debug("No command named {} has been registered!", ctx.getMessage().getCommand());
            }
        }
    }

    private void call(Command cmd, IGCFMessageContext ctx) {
        if (defaultLimiters != null) {
            for (IExecutionLimiter limiter : defaultLimiters) {
                if (!limiter.check(ctx)) {
                    log.debug("Rejected command execution based on global-scoped limiters!");
                    limiter.onFail(ctx);
                    return;
                }
            }
        }

        if (cmd.getCommandLimiters() != null) {
            for (IExecutionLimiter limiter : cmd.getCommandLimiters()) {
                if (!limiter.check(ctx)) {
                    log.debug("Rejected command execution based on command-scoped limiters!");
                    limiter.onFail(ctx);
                    return;
                }
            }
        }

        cmd.getOnExecution().accept(ctx);
    }

    /**
     * Registers a command in the commandRegistry
     *
     * @param command The command to register.
     */
    public void register(Command command) {
        commands.put(command.getName().toLowerCase(), command);
        if (command.getAliases() != null) {
            command.getAliases().forEach(alias -> commands.put(alias.toLowerCase(), command));
        }
        log.info("Registered command {} [Aliases: {}]", command.getName(), command.getAliases());
    }

    /**
     * Optionally returns the command associated with the given name;
     *
     * @param name The name to look up.
     * @return The command associated with the given name.
     */
    public Optional<Command> getCommand(String name) {
        return Optional.ofNullable(commands.get(name.toLowerCase()));
    }

}