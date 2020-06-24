package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IMessageInstance;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MessageInstance implements IMessageInstance {

    // Type
    private final String type;

    // Unique Id
    private final String id;

    // Key
    private final String key;

    @Builder
    public MessageInstance(String type, String id) {
        this.type = type;
        this.id = id;
        this.key = type + "-" + id;
    }

}
