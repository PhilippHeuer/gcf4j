package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbed;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbedField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
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

    /**
     * {@inheritDoc}
     */
    private Map<String, Object> data;

    /**
     * {@inheritDoc}
     */
    private String alternativeText;

}
