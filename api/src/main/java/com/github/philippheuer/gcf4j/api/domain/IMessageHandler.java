package com.github.philippheuer.gcf4j.api.domain;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;

import java.util.Set;
import java.util.function.Consumer;

public interface IMessageHandler {

    /**
     * Gets the function that is executed when the command is called.
     *
     * @return The function that is executed when the command is called.
     */
    Consumer<IMessageContext> getHandler();

    /**
     * Gets the limiters which determine if the command can be executed in a given context.
     *
     * @return The limiters which determine if the command can be executed in a given context.
     */
    Set<IExecutionLimiter> getExecutionLimiters();

}
