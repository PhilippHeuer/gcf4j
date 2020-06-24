package com.github.philippheuer.gcf4j.core.util;

import com.github.philippheuer.gcf4j.api.domain.IMessage;
import com.github.philippheuer.gcf4j.api.domain.IMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IMessageResponder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyResponder implements IMessageResponder {

    @Override
    public void sendMessage(IMessageContext messageContext, IMessage message) {
        log.info("Sending message in {} - {} with content: {}!", messageContext.getInstance().getKey(), messageContext.getChannel().getName(), message.getText());
    }

}
