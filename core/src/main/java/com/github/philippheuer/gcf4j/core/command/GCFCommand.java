package com.github.philippheuer.gcf4j.core.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.command.IGCFCommand;
import com.github.philippheuer.gcf4j.api.command.IGCFCommandContext;
import com.github.philippheuer.gcf4j.api.command.IGCFCommandOption;
import com.github.philippheuer.gcf4j.api.command.IGCFUsageExample;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.core.domain.GCFMessage;
import com.github.philippheuer.gcf4j.core.domain.GCFMessageEmbed;
import com.github.philippheuer.gcf4j.core.util.OptionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.Accessors;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class GCFCommand implements IGCFCommand {

    @Getter
    private final Options OPTIONS = new Options();

    private String name;

    private Set<String> aliases;

    private String category;

    protected String description;

    @Getter
    private List<IGCFCommandOption> commandOptions;

    private List<IGCFUsageExample> usageExamples;

    @Accessors(fluent = true)
    private Boolean isVisibleToEveryone = false;

    /**
     * Constructor
     *
     * @param name command name
     * @param aliases command aliases
     * @param category command category
     * @param description holds a general description about the command
     */
    public GCFCommand(String name, Set<String> aliases, String category, String description) {
        this.name = name;
        this.aliases = aliases;
        this.category = category;
        this.description = description;
    }

    /**
     * Constructor
     *
     * @param name command name
     * @param aliases command aliases
     * @param category command category
     * @param description holds a general description about the command
     * @param options list of command options / parameters
     * @param usageExamples contains a list of usage examples
     */
    public GCFCommand(String name, Set<String> aliases, String category, String description, List<IGCFCommandOption> options, List<IGCFUsageExample> usageExamples) {
        this.name = name;
        this.aliases = aliases;
        this.category = category;
        this.description = description;

        registerCommandOption(options.toArray(new IGCFCommandOption[0]));
        this.usageExamples = usageExamples;
    }

    @Getter
    @Singular
    protected Set<IExecutionLimiter> commandLimiters;

    public void registerCommandOption(IGCFCommandOption... options) {
        if (commandOptions == null) {
            commandOptions = new ArrayList<>();
        }

        Set.of(options).forEach(opt -> {
            commandOptions.add(opt);

            var apacheCliOption = Option.builder(opt.getShortOpt()).longOpt(opt.getLongOpt()).hasArg(opt.hasArgument()).desc(opt.getDescription()).optionalArg(!opt.isRequired()).build();
            getOPTIONS().addOption(apacheCliOption);
        });
    }

    public void addUsageExample(IGCFUsageExample... examples) {
        if (usageExamples == null) {
            usageExamples = new ArrayList<>();
        }

        Set.of(examples).forEach(ex -> {
            usageExamples.add(ex);
        });
    }

    public void setVisibleToEveryone(Boolean visible) {
        this.isVisibleToEveryone = true;
    }

    public IGCFMessageContext onSuperExecution(IGCFMessageContext ctx) {
        var commandContext = new GCFCommandContext(ctx, OptionUtils.parseCommandLineArgs(OPTIONS, ctx.getMessage().getCommandPayload()), this);
        return onExecution(commandContext);
    }

    @Override
    public abstract IGCFMessageContext onExecution(IGCFCommandContext ctx);

    public void respondWithPlainTextMessage(IGCFMessageContext ctx, String content) {
        ctx.getConnector().sendMessage(ctx, GCFMessage.builder().text(content).build());
    }

    public void respondWithTextMessage(IGCFMessageContext ctx, String title, String content, Color color) {
        ctx.getConnector().sendMessage(ctx, GCFMessage.builder().text(content).messageEmbed(GCFMessageEmbed.builder()
                .color(color)
                .title(title)
                .description(content).build()
        ).build());
    }

    public void respondWithUnknownCommand(String cmdName, IGCFMessageContext ctx) {
        var permErrorMsg = GCFMessageEmbed.builder()
                .color(Color.RED)
                .title("Unknown Command: " + cmdName)
                .description(cmdName + " is not a valid command")
                .build();
        ctx.getConnector().sendMessage(ctx, GCFMessage.builder().messageEmbed(permErrorMsg).build());
    }
}
