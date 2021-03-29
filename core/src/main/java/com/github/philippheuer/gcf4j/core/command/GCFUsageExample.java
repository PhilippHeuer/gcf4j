package com.github.philippheuer.gcf4j.core.command;

import com.github.philippheuer.gcf4j.api.command.IGCFUsageExample;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GCFUsageExample implements IGCFUsageExample {

    private String command;

    private String description;

}
