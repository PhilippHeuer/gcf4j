package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import lombok.*;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Command {

    private String name;

    private Set<String> aliases;

    /**
     * Get a help/description text
     */
    protected String description;

    /**
     * An example on how to use the command
     */
    protected Map<String, String> usageExample;

    /**
     * Constructor
     *
     * @param name command name
     * @param aliases command aliases
     * @param description holds a general description about the command
     */
    public Command(String name, Set<String> aliases, String description) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
    }

    /**
     * Gets the function that is executed when the command is called.
     *
     * @return The function that is executed when the command is called.
     */
    @Getter
    protected Consumer<IGCFMessageContext> onExecution;

    /**
     * Gets the limiters which determine if the command can be executed in a given context.
     *
     * @return The limiters which determine if the command can be executed in a given context.
     */
    @Getter
    @Singular
    protected Set<IExecutionLimiter> commandLimiters;

}
