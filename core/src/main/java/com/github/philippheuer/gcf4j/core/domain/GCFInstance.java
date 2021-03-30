package com.github.philippheuer.gcf4j.core.domain;

import com.github.philippheuer.gcf4j.api.domain.IGCFInstance;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
