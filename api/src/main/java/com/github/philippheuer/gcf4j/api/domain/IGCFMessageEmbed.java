package com.github.philippheuer.gcf4j.api.domain;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;

public interface IGCFMessageEmbed {

    String getTitle();

    String getDescription();

    String getUrl();

    String getFooter();

    Color getColor();

    List<IGCFMessageEmbedField> getFields();

    TemporalAccessor getTimestamp();

    /**
     * @return data that is used to render title / description / footer / alternative text
     */
    Map<String, Object> getData();

    /**
     * @return alternative text if embeds are not supported
     */
    String getAlternativeText();

}
