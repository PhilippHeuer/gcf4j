package com.github.philippheuer.gcf4j.api.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICommandManager {

    /**
     * @return gets the globally defined limiters for command execution
     */
    Set<IExecutionLimiter> getDefaultLimiters();

    /**
     * registers a new command
     *
     * @param command command
     * @return true, if the command was added successfully
     */
    boolean register(IGCFCommand command);

    /**
     * @return a unmodifiable list of all commands
     */
    List<? extends IGCFCommand> getCommands();

    /**
     * optionally returns the command associated with the given name;
     *
     * @param name The name to look up.
     * @return The command associated with the given name.
     */
    Optional<IGCFCommand> getCommand(String name);

    /**
     * is this message a command?
     *
     * @param ctx the message context
     * @return true if the message contains a existing command regardless of permissions
     */
    boolean isCommand(IGCFMessageContext ctx);

    /**
     * has permissions to run the command?
     *
     * @param ctx the message context
     * @return true if the user has permissions to run the command
     */
    boolean hasPermissions(IGCFMessageContext ctx);

    /**
     * has permissions to run the command?
     *
     * @param ctx the message context
     * @param commandName name of the target command
     * @return true if the user has permissions to run the command
     */
    boolean hasPermissions(IGCFMessageContext ctx, String commandName);

    /**
     * Parses the message context and returns a message that contains the command / command arguments
     *
     * @param ctx the message context
     * @return true if the message contains a command, false otherwise
     */
    boolean parseMessage(IGCFMessageContext ctx, Set<String> triggers);

    /**
     * Run Command
     *
     * @param ctx the message context, parseMessage needs to be called on the ctx before calling this
     */
    IGCFMessageContext runCommand(IGCFMessageContext ctx);
}
