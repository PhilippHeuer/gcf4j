package com.github.philippheuer.gcf4j.core.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.command.GCFCommandScopes;
import com.github.philippheuer.gcf4j.api.command.IGCFCommand;
import com.github.philippheuer.gcf4j.api.command.IGCFCommandContext;
import com.github.philippheuer.gcf4j.api.command.IGCFCommandOption;
import com.github.philippheuer.gcf4j.api.command.IGCFUsageExample;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessage;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbed;
import com.github.philippheuer.gcf4j.core.domain.GCFMessage;
import com.github.philippheuer.gcf4j.core.domain.GCFMessageContext;
import com.github.philippheuer.gcf4j.core.domain.GCFMessageEmbed;
import com.github.philippheuer.gcf4j.core.domain.GCFMessageEmbedField;
import com.github.philippheuer.gcf4j.core.util.OptionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class GCFCommand implements IGCFCommand {

    @Getter
    private final Options OPTIONS = new Options();

    protected String name;

    protected Set<String> aliases;

    protected String category;

    protected String description;

    @Setter
    protected String scope;

    @Setter
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
     * @param isVisibleToEveryone command is visible?
     * @param scope command scope
     */
    public GCFCommand(String name, Set<String> aliases, String category, String description, Boolean isVisibleToEveryone, GCFCommandScopes scope) {
        this.name = name;
        this.aliases = aliases;
        this.category = category;
        this.description = description;
        this.isVisibleToEveryone = isVisibleToEveryone;
        this.scope = scope.name();
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
     * @param isVisibleToEveryone command is visible?
     * @param scope command scope
     */
    public GCFCommand(String name, Set<String> aliases, String category, String description, List<IGCFCommandOption> options, List<IGCFUsageExample> usageExamples, Boolean isVisibleToEveryone, GCFCommandScopes scope) {
        this.name = name;
        this.aliases = aliases;
        this.category = category;
        this.description = description;

        registerCommandOption(options.toArray(new IGCFCommandOption[0]));
        addUsageExample(usageExamples.toArray(new IGCFUsageExample[0]));

        this.isVisibleToEveryone = isVisibleToEveryone;
        this.scope = scope.name();
    }

    @Getter
    protected Set<IExecutionLimiter> commandLimiters = new HashSet<>();

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

    public IGCFMessageContext respondWithMessage(IGCFMessageContext ctx, IGCFMessage message) {
        return GCFMessageContext.replaceMessage(ctx, message);
    }

    public IGCFMessageContext respondWithSimpleMessage(IGCFMessageContext ctx, String title, String content, Color color) {
        var messageEmbed = GCFMessageEmbed.builder()
                .title(title)
                .description(content)
                .color(color)
                .build();

        return respondWithMessage(ctx, GCFMessage.builder().text(content).messageEmbed(messageEmbed).build());
    }

    public IGCFMessageContext respondWithUnknownCommand(IGCFMessageContext ctx, String cmdName) {
        var permErrorMsg = GCFMessageEmbed.builder()
                .color(Color.RED)
                .title("Unknown Command: " + cmdName)
                .description(cmdName + " is not a valid command")
                .build();

        return respondWithMessage(ctx, GCFMessage.builder().text(permErrorMsg.getDescription()).messageEmbed(permErrorMsg).ephemeral(true).build());
    }

    public IGCFMessageContext respondWithSyntaxErrorHelp(IGCFMessageContext ctx, IGCFCommand command) {
        GCFMessageEmbed.GCFMessageEmbedBuilder embed = GCFMessageEmbed.builder()
                .color(Color.RED)
                .title("Syntax Error [" + command.getName() + "]")
                .description(ctx.getMessage().getText() != null ? ctx.getMessage().getText() : "");

        StringBuffer examplesStringBuffer = new StringBuffer();
        if (command.getUsageExamples() != null && command.getUsageExamples().size() > 0) {
            command.getUsageExamples().forEach(example -> {
                examplesStringBuffer.append(String.format("%s %s (%s)\n", command.getName(), example.getCommand(), example.getDescription()));
            });
        }
        embed = embed.field(GCFMessageEmbedField.builder().key("Examples").value(examplesStringBuffer.toString()).inline(false).build());

        return respondWithMessage(ctx, GCFMessage.builder().text("Syntax Error!").messageEmbed(embed.build()).ephemeral(true).build());
    }
}
