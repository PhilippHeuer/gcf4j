package com.github.philippheuer.gcf4j.api.command;

import com.github.philippheuer.gcf4j.api.IExecutionLimiter;

import java.util.List;

public interface IGCFCommandOption {

    /**
     * @return the short option name, typically just one letter
     */
    String getShortOpt();

    /**
     * @return the long option name, the full name
     */
    String getLongOpt();

    /**
     * @return description
     */
    String getDescription();

    /**
     * @return type of the option
     */
    GCFCommandOptionType getType();

    /**
     * @return is this argument required?
     */
    boolean isRequired();

    /**
     * @return gets the default value
     */
    String getDefaultValue();

    /**
     * @return contains a list of all allowed values if applicable
     */
    List<String> getChoices();

    /**
     * @return returns the limiters for this option
     */
    List<IExecutionLimiter> getLimiters();

    /**
     * @return does this option have a value, returns false for subcommands / flags
     */
    default boolean hasArgument() {
        if (getType().equals(GCFCommandOptionType.SUB_COMMAND) || getType().equals(GCFCommandOptionType.FLAG)) {
            return false;
        }

        return true;
    }

}
