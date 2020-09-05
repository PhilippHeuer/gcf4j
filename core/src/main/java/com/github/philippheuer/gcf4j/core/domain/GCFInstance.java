package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFInstance;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class GCFInstance implements IGCFInstance {

    // Type
    private String type;

    // Unique Id
    private String id;

    @Builder
    public GCFInstance(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getKey() {
        return type + "-" + id;
    }

}
