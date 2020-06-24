package com.github.philippheuer.gcf4j.core;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.IMessageHandlerRegistry;
import com.github.philippheuer.gcf4j.api.domain.IMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IMessageHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MessageHandlerRegistry implements IMessageHandlerRegistry {

    // holds all registered message handlers
    private final Set<IMessageHandler> handlers = new HashSet<>();

    // holds the default execution limiters
    @Getter
    private Set<IExecutionLimiter> defaultLimiters;

    /**
     * Registers a command in the commandRegistry
     *
     * @param handler The command handler.
     */
    public void register(IMessageHandler handler) {
        log.info("Registered message handler {}", handler.getClass().getSimpleName());
        handlers.add(handler);
    }

    /**
     * {@inheritDoc}
     */
    public void call(IMessageContext ctx) {
        if (defaultLimiters != null) {
            for (IExecutionLimiter limiter : defaultLimiters) {
                if (!limiter.check(ctx)) {
                    limiter.onFail(ctx);
                    return;
                }
            }
        }

        if (handlers != null) {
            handlers.forEach(handler -> {
                for (IExecutionLimiter limiter : handler.getExecutionLimiters()) {
                    if (!limiter.check(ctx)) {
                        limiter.onFail(ctx);
                        return;
                    }
                }

                handler.getHandler().accept(ctx);
            });
        }
    }
}
