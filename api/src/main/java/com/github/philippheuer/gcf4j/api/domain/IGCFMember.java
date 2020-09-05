package com.github.philippheuer.gcf4j.api.domain;

import java.util.Set;

public interface IGCFMember {

    /**
     * Unique User Id
     */
    String getId();

    /**
     * User Name
     */
    String getName();

    /**
     * User Display Name
     */
    String getDisplayName();

    /**
     * Avatar Url
     */
    String getAvatarUrl();

    /**
     * Get a string to mention the user
     */
    String getMention();

    /**
     * Get user stauts
     */
    String getStatus();

    /**
     * is the user a bot?
     */
    boolean isBot();

    /**
     * get user roles
     */
    Set<IGCFRole> getRoles();

    /**
     * get origin
     */
    Object getOrigin();

}
