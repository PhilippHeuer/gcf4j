package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFReaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
