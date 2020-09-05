package com.github.philippheuer.gcf4j.api.domain;

/**
 * Implements method to respond to a command
 */
public interface IGCFMessageResponder {

    /**
     * Sends a direct response related to the IMessageContext
     *
     * @param messageContext the current message context, will be used to respond on the same instance + channel
     * @param message the message that should be send in response
     */
    void sendMessage(IGCFMessageContext messageContext, IGCFMessage message);

}
