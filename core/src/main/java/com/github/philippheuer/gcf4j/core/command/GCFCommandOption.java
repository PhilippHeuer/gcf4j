package com.github.philippheuer.gcf4j.core.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.command.GCFCommandOptionType;
import com.github.philippheuer.gcf4j.api.command.IGCFCommandOption;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Command Arguments
 */
@Builder
@Getter
public class GCFCommandOption implements IGCFCommandOption {

    private String shortOpt;

    private String longOpt;

    @Accessors(fluent = true)
    private boolean hasArgument;

    private String description;

    @Builder.Default
    private GCFCommandOptionType type = GCFCommandOptionType.UNKNOWN;

    @Builder.Default
    @Accessors(fluent = true)
    private boolean isRequired = false;

    private List<IExecutionLimiter> limiters;

    private String defaultValue;

    @Builder.Default
    private List<String> choices = List.of();
}
