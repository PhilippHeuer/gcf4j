package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFReaction;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GCFReaction implements IGCFReaction {

    /**
     * {@inheritDoc}
     */
    private String name;

    /**
     * {@inheritDoc}
     */
    private String type;

    /**
     * {@inheritDoc}
     */
    private Integer count;

}
