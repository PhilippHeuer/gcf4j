package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IMessageEmbed;
import com.github.philippheuer.gcf4j.api.domain.IMessageEmbedField;
import lombok.*;

import java.awt.*;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MessageEmbed implements IMessageEmbed {

    /**
     * {@inheritDoc}
     */
    private String title;

    /**
     * {@inheritDoc}
     */
    private String description;

    /**
     * {@inheritDoc}
     */
    private String url;

    /**
     * {@inheritDoc}
     */
    private Color color;

    /**
     * {@inheritDoc}
     */
    @Singular
    private List<IMessageEmbedField> fields;

}
