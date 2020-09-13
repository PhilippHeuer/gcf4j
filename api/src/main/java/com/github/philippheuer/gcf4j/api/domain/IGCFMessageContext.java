package com.github.philippheuer.gcf4j.api.domain;

import com.github.philippheuer.gcf4j.api.IMessageConnector;

public interface IGCFMessageContext {

    IGCFInstance getInstance();

    IGCFChannel getChannel();

    IGCFMember getAuthor();

    IGCFMessage getMessage();

    IMessageConnector getConnector();
}
