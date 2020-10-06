package com.github.philippheuer.gcf4j.api.domain;

import java.time.Duration;
import java.util.List;

public interface IGCFMessage {

    /**
     * the unique id of the message
     */
    String getId();

    /**
     * the message text
     */
    String getText();

    /**
     * {@inheritDoc}
     */
    String getThreadId();

    /**
     * a list of all attachments that are part of the message
     */
    List<IGCFMessageAttachment> getAttachments();

    /**
     * gets the embed message info
     */
    IGCFMessageEmbed getMessageEmbed();

    /**
     * returns the command in case a command was fired
     * @return
     */
    String getCommand();

    /**
     * returns the payload after the command for futher processing
     * @return
     */
    String getCommandPayload();

    /**
     * returns the amount of time before a message should self-destruct
     * @return
     */
    Duration getSelfDestruct();

    /**
     * returns the reactions of the message
     * @return
     */
    List<IGCFReaction> getReactions();

}
