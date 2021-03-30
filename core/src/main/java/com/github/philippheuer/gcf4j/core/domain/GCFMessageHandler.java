package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.Set;
import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GCFMessageHandler implements IGCFMessageHandler {

    /**
     * {@inheritDoc}
     */
    @Getter
    protected Consumer<IGCFMessageContext> handler;

    /**
     * {@inheritDoc}
     */
    @Getter
    @Singular
    protected Set<IExecutionLimiter> executionLimiters;

}
