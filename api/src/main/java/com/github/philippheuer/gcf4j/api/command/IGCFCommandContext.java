package com.github.philippheuer.gcf4j.api.command;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;

import java.util.List;

public interface IGCFCommandContext extends IGCFMessageContext {

    /**
     * Has the command the specified option?
     *
     * @param option option name
     * @return true if the option is present
     */
    boolean hasOption(IGCFCommandOption option);

    /**
     * Gets the value of a option
     *
     * @param option option name
     * @return text value of the option or null
     */
    String getOptionValue(IGCFCommandOption option);

    /**
     * Gets the list of arguments that are not options
     *
     * @return list of all arguments
     */
    List<String> getArgumentList();

    /**
     * Gets the command payload without any options / option values
     *
     * @return text
     */
    String getArgumentsAsText();

}
