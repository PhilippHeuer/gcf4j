package com.github.philippheuer.gcf4j.api.domain;

public interface IMessageInstance {

    String getType();

    String getId();

    /**
     * Generates a unique key based on the instance
     *
     * @return key is generated with this template: type-id
     */
    String getKey();

}
