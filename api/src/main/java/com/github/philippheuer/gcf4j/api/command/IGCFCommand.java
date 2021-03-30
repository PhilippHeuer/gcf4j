package com.github.philippheuer.gcf4j.api.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;

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
     * send Plain Response
     *
     * @param ctx IGCFMessageContext
     */
    void respondWithPlainTextMessage(IGCFMessageContext ctx, String content);

    /**
     * send Embed Response
     *
     * @param ctx IGCFMessageContext
     */
    void respondWithTextMessage(IGCFMessageContext ctx, String title, String content, Color color);

    /**
     * command: unknown
     *
     * @param cmdName Command Name
     * @param ctx IGCFMessageContext
     */
    void respondWithUnknownCommand(String cmdName, IGCFMessageContext ctx);

}
