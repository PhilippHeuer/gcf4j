package com.github.philippheuer.gcf4j.api.domain;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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
     * sets a new value as command (used when parsing the message)
     *
     * @param command command
     */
    void setCommand(String command);

    /**
     * @return the command in case a command was fired
     */
    String getCommand();

    /**
     * sets the parameters for the command
     *
     * @param commandPayload command payload
     */
    void setCommandPayload(String commandPayload);

    /**
     * @return the payload after the command for further processing
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

    /**
     * @return true, if the response is ephemeral / only visible to the sender
     */
    Boolean getEphemeral();

    /**
     * @return gets a map with values used to render templates
     */
    Map<String, Object> getData();
}
