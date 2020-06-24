package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IMessageEmbedField;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MessageEmbedField implements IMessageEmbedField {

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
