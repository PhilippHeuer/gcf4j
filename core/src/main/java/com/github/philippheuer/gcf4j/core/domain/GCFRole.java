package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFRole;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class GCFRole implements IGCFRole {

    /**
     * {@inheritDoc}
     */
    private String id;

    /**
     * {@inheritDoc}
     */
    private String scope;

    /**
     * {@inheritDoc}
     */
    private String name;

}
