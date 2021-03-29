package com.github.philippheuer.gcf4j.core.util;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessage;
import com.github.philippheuer.gcf4j.core.domain.GCFMessage;

public class GCFUtils {

    /**
     * Convert to GCFMessage
     *
     * @param msg IGCFMessage
     * @return GCFMessage
     */
    public static GCFMessage toGCFMessage(IGCFMessage msg) {
        var builder = GCFMessage.builder()
                .id(msg.getId())
                .threadId(msg.getThreadId())
                .messageEmbed(msg.getMessageEmbed())
                .text(msg.getText())
                .attachments(msg.getAttachments())
                .command(msg.getCommand())
                .commandPayload(msg.getCommandPayload())
                .reactions(msg.getReactions())
                .selfDestruct(msg.getSelfDestruct());
        return builder.build();
    }

    /**
     * Convert to GCFMessage and insert command parameters
     *
     * @param msg IGCFMessage
     * @param command command name
     * @param commandPayload command payload
     * @return GCFMessage
     */
    public static GCFMessage toGCFMessage(IGCFMessage msg, String command, String commandPayload) {
        var builder = GCFMessage.builder()
                .id(msg.getId())
                .threadId(msg.getThreadId())
                .messageEmbed(msg.getMessageEmbed())
                .text(msg.getText())
                .attachments(msg.getAttachments())
                .command(command)
                .commandPayload(commandPayload)
                .reactions(msg.getReactions())
                .selfDestruct(msg.getSelfDestruct());
        return builder.build();
    }

}
