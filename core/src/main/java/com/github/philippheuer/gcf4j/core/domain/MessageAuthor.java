package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IMessageAuthor;
import com.github.philippheuer.gcf4j.api.domain.IRole;
import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MessageAuthor implements IMessageAuthor {

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
    private Set<IRole> roles;

    /**
     * {@inheritDoc}
     */
    private Set<String> globalRoles;

    /**
     * {@inheritDoc}
     */
    private Object origin;

}
