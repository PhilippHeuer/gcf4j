package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageEmbedField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class GCFMessageEmbedField implements IGCFMessageEmbedField {

    /**
     * {@inheritDoc}
     */
    private String key;

    /**
     * {@inheritDoc}
     */
    private String value;

    /**
     * {@inheritDoc}
     */
    @Builder.Default
    private boolean inline = true;

}
