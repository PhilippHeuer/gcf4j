package com.github.philippheuer.gcf4j.core.util;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;

import java.util.List;

public class LimiterUtils {

    /**
     * Checks how the provided context evaluates the specified limiters
     *
     * @param ctx the message context
     * @param limiters list of execution limiters
     * @return true if the checks in all limiters succeed
     */
    public static boolean checkLimiters(IGCFMessageContext ctx, List<IExecutionLimiter> limiters) {
        // global limiters
        if (limiters != null) {
            for (IExecutionLimiter limiter : limiters) {
                if (!limiter.check(ctx)) {
                    limiter.onFail(ctx);
                    return false;
                }
            }
        }

        return true;
    }

}
