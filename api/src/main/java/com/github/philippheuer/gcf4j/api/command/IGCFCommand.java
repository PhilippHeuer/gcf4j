package com.github.philippheuer.gcf4j.api.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbed;

import java.awt.*;
import java.util.List;
import java.util.Set;

public interface IGCFCommand {

    /**
     * @return the command name
     */
    String getName();

    /**
     * @return all available aliases for the command
     */
    Set<String> getAliases();

    /**
     * @return the command category
     */
    String getCategory();

    /**
     * @return the command description
     */
    String getDescription();

    /**
     * @return all command options
     */
    List<IGCFCommandOption> getCommandOptions();

    /**
     * @return all usage examples
     */
    List<IGCFUsageExample> getUsageExamples();

    /**
     * @return is the command visible to everyone regardless of usage permissions?
     */
    Boolean isVisibleToEveryone();

    /**
     * is the command visible to everyone regardless of usage permissions?
     *
     * @param visible is visible?
     */
    void setVisibleToEveryone(Boolean visible);

    /**
     * this will call the command specific onExecution after some preprocessing
     *
     * @param ctx IGCFMessageContext
     */
    IGCFMessageContext onSuperExecution(IGCFMessageContext ctx);

    /**
     * gets the function that is executed when the command is called.
     *
     * @return The function that is executed when the command is called.
     */
    IGCFMessageContext onExecution(IGCFCommandContext ctx);

    /**
     * gets the limiters which determine if the command can be executed in a given context.
     *
     * @return The limiters which determine if the command can be executed in a given context.
     */
    Set<IExecutionLimiter> getCommandLimiters();

    /**
     * registers one or multiple command options
     *
     * @param options Option...
     */
    void registerCommandOption(IGCFCommandOption... options);

    /**
     * adds new usage examples
     *
     * @param examples usage examples
     */
    void addUsageExample(IGCFUsageExample... examples);

    /**
     * responds with a message
     *
     * @param ctx IGCFMessageContext
     * @param messageEmbed IGCFMessageEmbed
     * @return IGCFMessageContext of the response
     */
    IGCFMessageContext respondWithMessage(IGCFMessageContext ctx, IGCFMessageEmbed messageEmbed);

    /**
     * responds with a simple message
     *
     * @param ctx IGCFMessageContext
     * @param title title
     * @param content content
     * @param color color
     * @return IGCFMessageContext
     */
    IGCFMessageContext respondWithSimpleMessage(IGCFMessageContext ctx, String title, String content, Color color);

    /**
     * unknown command
     *
     * @param ctx IGCFMessageContext
     * @param cmdName command name
     * @return IGCFMessageContext of the response
     */
    IGCFMessageContext respondWithUnknownCommand(IGCFMessageContext ctx, String cmdName);

    /**
     * syntax error
     *
     * @param ctx IGCFMessageContext
     * @return IGCFMessageContext of the response
     */
    IGCFMessageContext respondWithSyntaxErrorHelp(IGCFMessageContext ctx, IGCFCommand command);

}
