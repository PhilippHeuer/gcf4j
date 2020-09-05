package com.github.philippheuer.gcf4j.api.domain;

public interface IGCFMessageContext {

    IGCFInstance getInstance();

    IGCFChannel getChannel();

    IGCFMember getAuthor();

    IGCFMessage getMessage();

    IGCFMessageResponder getResponder();
}
