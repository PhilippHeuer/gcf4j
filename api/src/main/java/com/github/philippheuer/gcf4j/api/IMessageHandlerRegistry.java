package com.github.philippheuer.gcf4j.api;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;

public interface IMessageHandlerRegistry {

    /**
     * Calls all registered message handlers if no limiter restricts execution
     *
     * @param ctx The context to give to the command function.
     */
    void call(IGCFMessageContext ctx);

}
