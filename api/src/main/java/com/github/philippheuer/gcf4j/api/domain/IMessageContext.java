package com.github.philippheuer.gcf4j.api.domain;

public interface IMessageContext {

    IMessageInstance getInstance();

    IMessageChannel getChannel();

    IMessageAuthor getAuthor();

    IMessage getMessage();

    IMessageResponder getResponder();
}
