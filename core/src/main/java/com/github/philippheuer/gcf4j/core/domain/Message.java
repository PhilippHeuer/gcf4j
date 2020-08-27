package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IMessage;
import com.github.philippheuer.gcf4j.api.domain.IMessageAttachment;
import com.github.philippheuer.gcf4j.api.domain.IMessageEmbed;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Message implements IMessage {

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
    @Singular
    private List<IMessageAttachment> attachments;

    /**
     * {@inheritDoc}
     */
    private IMessageEmbed messageEmbed;

    /**
     * {@inheritDoc}
     */
    private String command;

    /**
     * {@inheritDoc}
     */
    private String commandPayload;

    /**
     * {@inheritDoc}
     */
    private Duration selfDestruct;

}
