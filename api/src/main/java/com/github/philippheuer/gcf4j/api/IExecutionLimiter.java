package com.github.philippheuer.gcf4j.api;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;

/**
 * Used to limit the execution of a command to a certain context.
 */
@FunctionalInterface
public interface IExecutionLimiter {

    /**
     * Determines if the command can be executed in the given context.
     *
     * @param ctx The context in which the command was executed.
     * @return True if the command can be executed.
     */
    boolean check(IGCFMessageContext ctx);

    /**
     * Executed by the {@link ICommandRegistry} if a command is called and {@link #check(IGCFMessageContext)} returns false.
     *
     * @param ctx The context in which the limiter failed.
     */
    default void onFail(IGCFMessageContext ctx) {
        // NO-OP
    }
}