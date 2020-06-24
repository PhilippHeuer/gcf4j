package com.github.philippheuer.gcf4j.api.domain;

import java.util.Set;

public interface IMessageAuthor {

    String getId();

    String getName();

    boolean isBot();

    Set<String> getRoles();

}
