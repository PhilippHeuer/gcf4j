package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IMessageHandler;
import lombok.*;

import java.util.Set;
import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageHandler implements IMessageHandler {

    /**
     * {@inheritDoc}
     */
    @Getter
    protected Consumer<IMessageContext> handler;

    /**
     * {@inheritDoc}
     */
    @Getter
    @Singular
    protected Set<IExecutionLimiter> executionLimiters;

}
