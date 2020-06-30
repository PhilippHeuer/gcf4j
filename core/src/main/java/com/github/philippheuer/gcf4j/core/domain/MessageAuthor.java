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

    // Unique UserId
    private String id;

    // Display Name
    private String name;

    // Is Bot?
    @Builder.Default
    private boolean bot = false;

    // Roles
    private Set<IRole> roles;

    // Global Roles
    private Set<String> globalRoles;

}
