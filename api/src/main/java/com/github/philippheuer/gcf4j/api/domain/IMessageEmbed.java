package com.github.philippheuer.gcf4j.api.domain;

import java.awt.*;
import java.util.List;

public interface IMessageEmbed {

    String getTitle();

    String getDescription();

    String getUrl();

    Color getColor();

    List<IMessageEmbedField> getFields();

}
