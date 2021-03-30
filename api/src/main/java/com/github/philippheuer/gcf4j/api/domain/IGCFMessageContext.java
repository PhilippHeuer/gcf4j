package com.github.philippheuer.gcf4j.api.domain;

import com.github.philippheuer.gcf4j.api.IMessageConnector;
import io.opentracing.Span;

public interface IGCFMessageContext {

    Span getSpan();

    IGCFInstance getInstance();

    IGCFChannel getChannel();

    IGCFMember getAuthor();

    IGCFMessage getMessage();

    IGCFMember getBotMember();

    IMessageConnector getConnector();

}
