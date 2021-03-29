package com.github.philippheuer.gcf4j.api.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

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
     * This will call the command specific onExecution after some preprocessing
     *
     * @param ctx IGCFMessageContext
     */
    void onSuperExecution(IGCFMessageContext ctx);

    /**
     * Gets the function that is executed when the command is called.
     *
     * @return The function that is executed when the command is called.
     */
    Consumer<IGCFMessageContext> getOnExecution();

    /**
     * Gets the limiters which determine if the command can be executed in a given context.
     *
     * @return The limiters which determine if the command can be executed in a given context.
     */
    Set<IExecutionLimiter> getCommandLimiters();

    /**
     * Registers one or multiple command options
     *
     * @param options Option...
     */
    void registerCommandOption(IGCFCommandOption... options);

    /**
     * Send Plain Response
     *
     * @param ctx IGCFMessageContext
     */
    void respondWithPlainTextMessage(IGCFMessageContext ctx, String content);

    /**
     * Send Embed Response
     *
     * @param ctx IGCFMessageContext
     */
    void respondWithTextMessage(IGCFMessageContext ctx, String title, String content, Color color);

    /**
     * Command: Unknown
     *
     * @param cmdName Command Name
     * @param ctx IGCFMessageContext
     */
    void respondWithUnknownCommand(String cmdName, IGCFMessageContext ctx);

}
