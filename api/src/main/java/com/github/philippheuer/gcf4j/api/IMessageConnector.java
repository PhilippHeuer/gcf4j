package com.github.philippheuer.gcf4j.api;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessage;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageType;

import java.util.Set;

/**
 * Implements method to respond to a command
 */
public interface IMessageConnector {

    /**
     * Sends a direct response related to the IMessageContext
     *
     * @param messageContext the current message context, will be used to respond on the same instance + channel
     * @param message the message that should be send in response
     */
    void sendMessage(IGCFMessageContext messageContext, IGCFMessage message);

    /**
     * Sends a direct response related to the IMessageContext
     *
     * @param messageContext the current message context, will be used to respond on the same instance + channel
     * @param message the message that should be deleted, a message id has to be set
     */
    void deleteMessage(IGCFMessageContext messageContext, IGCFMessage message);

    /**
     * @return a set of all supported message types
     */
    Set<IGCFMessageType> getSupportedMessageTypes();

    /**
     * @return the default message type
     */
    IGCFMessageType getDefaultMessageType();
}

