package com.github.philippheuer.gcf4j.core.limiter;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Limits execution of a command to a set of users.
 */
public class UserLimiter implements IExecutionLimiter {

    private final String[] userIds;

    public UserLimiter(String... userIds) {
        this.userIds = userIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(IGCFMessageContext ctx) {
        return ArrayUtils.contains(userIds, null);
    }

}