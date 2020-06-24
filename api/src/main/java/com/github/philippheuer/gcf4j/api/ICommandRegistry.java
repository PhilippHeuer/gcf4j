package com.github.philippheuer.gcf4j.api;

import com.github.philippheuer.gcf4j.api.domain.IMessageContext;

import java.util.Set;

public interface ICommandRegistry {

    /**
     * Calls a command with the given name, if it exists.
     *
     * @param ctx The context to give to the command function.
     */
    void call(IMessageContext ctx);

    /**
     * Gets the default command limiters
     *
     * @return a set of command limiters
     */
    Set<IExecutionLimiter> getDefaultLimiters();

}
