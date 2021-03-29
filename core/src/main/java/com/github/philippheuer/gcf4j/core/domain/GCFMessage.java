package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessage;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageAttachment;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbed;
import com.github.philippheuer.gcf4j.api.domain.IGCFReaction;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class GCFMessage implements IGCFMessage {

    /**
     * {@inheritDoc}
     */
    private String id;

    /**
     * {@inheritDoc}
     */
    private String text;

    /**
     * {@inheritDoc}
     */
    private String threadId;

    /**
     * {@inheritDoc}
     */
    @Singular
    private List<IGCFMessageAttachment> attachments;

    /**
     * {@inheritDoc}
     */
    private IGCFMessageEmbed messageEmbed;

    /**
     * {@inheritDoc}
     */
    @Setter
    private String command;

    /**
     * {@inheritDoc}
     */
    @Setter
    private String commandPayload;

    /**
     * {@inheritDoc}
     */
    List<IGCFReaction> reactions;

    /**
     * {@inheritDoc}
     */
    private Duration selfDestruct;

}
