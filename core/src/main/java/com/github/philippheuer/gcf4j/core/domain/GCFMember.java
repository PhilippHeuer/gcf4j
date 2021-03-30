package com.github.philippheuer.gcf4j.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.philippheuer.gcf4j.api.domain.IGCFMember;
import com.github.philippheuer.gcf4j.api.domain.IGCFRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class GCFMember implements IGCFMember {

    /**
     * {@inheritDoc}
     */
    private String id;

    /**
     * {@inheritDoc}
     */
    private String name;

    /**
     * {@inheritDoc}
     */
    private String displayName;

    /**
     * {@inheritDoc}
     */
    private String avatarUrl;

    /**
     * {@inheritDoc}
     */
    private String mention;

    /**
     * {@inheritDoc}
     */
    private String status;

    /**
     * {@inheritDoc}
     */
    @Builder.Default
    private boolean bot = false;

    /**
     * {@inheritDoc}
     */
    private Set<IGCFRole> roles;

    /**
     * {@inheritDoc}
     */
    @JsonIgnore
    private Object origin;

}
