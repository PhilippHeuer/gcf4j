package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessage;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageAttachment;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbed;
import com.github.philippheuer.gcf4j.api.domain.IGCFReaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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

    /**
     * {@inheritDoc}
     */
    @Accessors(prefix = "")
    private Boolean ephemeral;

    /**
     * {@inheritDoc}
     */
    private Map<String, Object> data;

}