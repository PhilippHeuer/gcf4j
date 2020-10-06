package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbed;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbedField;
import lombok.*;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class GCFMessageEmbed implements IGCFMessageEmbed {

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
    private String footer;

    /**
     * {@inheritDoc}
     */
    @Singular
    private List<IGCFMessageEmbedField> fields;

    /**
     * {@inheritDoc}
     */
    private TemporalAccessor timestamp;

}
