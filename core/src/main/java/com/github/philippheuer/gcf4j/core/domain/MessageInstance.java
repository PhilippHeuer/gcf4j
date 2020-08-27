package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IMessageInstance;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class MessageInstance implements IMessageInstance {

    // Type
    private String type;

    // Unique Id
    private String id;

    // Key
    private String key;

    @Builder
    public MessageInstance(String type, String id) {
        this.type = type;
        this.id = id;
        this.key = type + "-" + id;
    }

}
