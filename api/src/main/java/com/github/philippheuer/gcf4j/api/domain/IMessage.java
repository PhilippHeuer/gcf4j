package com.github.philippheuer.gcf4j.api.domain;

import java.time.Duration;
import java.util.List;

public interface IMessage {

    /**
     * the unique id of the message
     */
    String getId();

    /**
     * the message text
     */
    String getText();

    /**
     * a list of all attachments that are part of the message
     */
    List<IMessageAttachment> getAttachments();

    /**
     * gets the embed message info
     */
    IMessageEmbed getMessageEmbed();

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

}
