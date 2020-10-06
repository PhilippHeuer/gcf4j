package com.github.philippheuer.gcf4j.api.domain;

public interface IGCFReaction {

    /**
     * @return name of the reaction
     */
    String getName();

    /**
     * @return type of the reaction (unicode/emote)
     */
    String getType();

    /**
     * @return total count of reactions for this type
     */
    Integer getCount();

}
