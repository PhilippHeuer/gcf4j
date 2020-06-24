package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IMessageChannel;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MessageChannel implements IMessageChannel {

    // Unique ChannelId
    private String id;

    // Name
    private String name;

}
