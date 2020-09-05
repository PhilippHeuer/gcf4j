package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFChannel;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class GCFChannel implements IGCFChannel {

    // Unique ChannelId
    private String id;

    // Name
    private String name;

}
