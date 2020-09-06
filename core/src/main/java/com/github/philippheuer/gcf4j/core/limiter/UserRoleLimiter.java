package com.github.philippheuer.gcf4j.core.limiter;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IGCFRole;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A UserRoleLimiter restricts the execution to a few specified roles
 */
@Slf4j
public class UserRoleLimiter implements IExecutionLimiter {

    private final Set<IGCFRole> allowedRoles;

    /**
     * Constructs a new ReleaseBranchLimiter
     *
     * @param allowedRoles The roles allowed to use this command
     */
    public UserRoleLimiter(Set<IGCFRole> allowedRoles) {
        this.allowedRoles = allowedRoles;
    }

    @Override
    public boolean check(IGCFMessageContext ctx) {
        log.debug("{} checking if user {} [{}] has one of the allowed rules {}.", getClass().getSimpleName(), ctx.getAuthor().getId(), ctx.getAuthor().getRoles(), allowedRoles.stream().map(r -> r.getId()).collect(Collectors.joining(", ")));

        for (var role : allowedRoles) {
            for (var userRole : ctx.getAuthor().getRoles()) {
                if (userRole.getName().equalsIgnoreCase(role.getName()) && userRole.getScope().equalsIgnoreCase(role.getScope())) {
                    return true;
                } else if (userRole.getId().equalsIgnoreCase(role.getId()) && userRole.getScope().equalsIgnoreCase(role.getScope())) {
                    return true;
                }
            }
        }

        return false;
    }

}
