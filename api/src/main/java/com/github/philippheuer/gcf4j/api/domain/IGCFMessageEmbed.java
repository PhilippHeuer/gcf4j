package com.github.philippheuer.gcf4j.api.domain;

import java.awt.*;
import java.util.List;

public interface IGCFMessageEmbed {

    String getTitle();

    String getDescription();

    String getUrl();

    Color getColor();

    List<IGCFMessageEmbedField> getFields();

}
