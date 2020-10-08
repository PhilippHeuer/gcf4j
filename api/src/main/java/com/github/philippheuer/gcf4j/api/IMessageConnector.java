package com.github.philippheuer.gcf4j.api;

import com.github.philippheuer.gcf4j.api.domain.*;

import java.time.Duration;
import java.util.Set;

/**
 * Implements method to respond to a command
 */
public interface IMessageConnector {

    /**
     * Gets the unique type identifier of this connector
     *
     * @return unique type identifier (discord, ...)
     */
    String getType();

    /**
     * Sends a direct response related to the IMessageContext
     *
     * @param messageContext the current message context, will be used to respond on the same instance + channel
     * @param message the message that should be send in response
     */
    void sendMessage(IGCFMessageContext messageContext, IGCFMessage message);

    /**
     * Edits a message
     *
     * @param messageContext the current message context, will be used to respond on the same instance + channel
     * @param message the message that should be edited, needs to contain the id
     */
    void editMessage(IGCFMessageContext messageContext, IGCFMessage message);

    /**
     * Sends a direct response related to the IMessageContext
     *
     * @param instance instance
     * @param recipient the target member
     * @param message the message that should be deleted, a message id has to be set
     */
    void sendPrivateMessage(IGCFInstance instance, IGCFMember recipient, IGCFMessage message);

    /**
     * Sends a direct response related to the IMessageContext
     *
     * @param messageContext the current message context, will be used to respond on the same instance + channel
     * @param message the message that should be deleted, a message id has to be set
     */
    void deleteMessage(IGCFMessageContext messageContext, IGCFMessage message);

    /**
     * Mute the specified member on the instance
     *
     * @param instance the instance
     * @param member the member
     */
    void muteMember(IGCFInstance instance, IGCFMember member);

    /**
     * Unmute the specified member on the instance
     *
     * @param instance the instance
     * @param member the member
     */
    void unmuteMember(IGCFInstance instance, IGCFMember member);

    /**
     * Ban the target user
     *
     * @param instance the instance
     * @param member the member
     * @param duration ban duration
     * @param reason ban reason
     */
    void banMember(IGCFInstance instance, IGCFMember member, Duration duration, String reason);

}

