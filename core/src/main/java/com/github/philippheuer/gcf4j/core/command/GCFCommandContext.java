package com.github.philippheuer.gcf4j.core.command;

import com.github.philippheuer.gcf4j.api.IMessageConnector;
import com.github.philippheuer.gcf4j.api.command.IGCFCommandOption;
import com.github.philippheuer.gcf4j.api.domain.IGCFChannel;
import com.github.philippheuer.gcf4j.api.domain.IGCFInstance;
import com.github.philippheuer.gcf4j.api.domain.IGCFMember;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessage;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.core.domain.GCFCommand;
import com.github.philippheuer.gcf4j.core.util.LimiterUtils;
import io.opentracing.Span;
import org.apache.commons.cli.CommandLine;

import java.util.List;

public class GCFCommandContext implements IGCFMessageContext {

    // holds the current context
    private IGCFMessageContext ctx;

    // holds the parsed command
    private CommandLine commandLine;

    private GCFCommand command;

    public GCFCommandContext(IGCFMessageContext ctx, CommandLine commandLine, GCFCommand command) {
        this.ctx = ctx;
        this.commandLine = commandLine;
        this.command = command;
    }

    @Override
    public Span getSpan() {
        return ctx.getSpan();
    }

    @Override
    public IGCFInstance getInstance() {
        return ctx.getInstance();
    }

    @Override
    public IGCFChannel getChannel() {
        return ctx.getChannel();
    }

    @Override
    public IGCFMember getAuthor() {
        return ctx.getAuthor();
    }

    @Override
    public IGCFMessage getMessage() {
        return ctx.getMessage();
    }

    @Override
    public IGCFMember getBotMember() {
        return ctx.getBotMember();
    }

    @Override
    public IMessageConnector getConnector() {
        return ctx.getConnector();
    }

    /**
     * Has the command the specified option?
     *
     * @param option option name
     * @return true if the option is present
     */
    public boolean hasOption(IGCFCommandOption option) {
        var optionPresent = commandLine.hasOption(option.getShortOpt());

        // ensures the option is present and checks permissions
        if (optionPresent && LimiterUtils.checkLimiters(ctx, option.getLimiters())) {
            return true;
        }

        return false;
    }

    /**
     * Gets the value of a option
     *
     * @param option option name
     * @return text value of the option or null
     */
    public String getOptionValue(IGCFCommandOption option) {
        // ensures the user has access to that option
        if (LimiterUtils.checkLimiters(ctx, option.getLimiters())) {
            var value = commandLine.getOptionValue(option.getShortOpt());
            if (value == null)
                return option.getDefaultValue();

            return value;
        }

        return null;
    }

    /**
     * Gets the list of arguments that are not options
     *
     * @return list of all arguments
     */
    public List<String> getArgumentList() {
        return commandLine.getArgList();
    }

    /**
     * Gets the command payload without any options / option values
     *
     * @return text
     */
    public String getArgumentsAsText() {
        var text = ctx.getMessage().getCommandPayload();

        for (IGCFCommandOption option : command.getCommandOptions()) {
            if (hasOption(option)) {
                if (option.hasArgument()) {
                    text = text.replace("--"+option.getLongOpt()+" "+getOptionValue(option), "");
                    text = text.replace("-"+option.getShortOpt()+" "+getOptionValue(option), "");
                } else {
                    text = text.replace("--"+option.getLongOpt(), "");
                    text = text.replace("-"+option.getShortOpt(), "");
                }
            }
        }

        return text.stripLeading();
    }
}
