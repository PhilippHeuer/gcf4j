package com.github.philippheuer.gcf4j.core.util;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessage;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageResponder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyResponder implements IGCFMessageResponder {

    @Override
    public void sendMessage(IGCFMessageContext messageContext, IGCFMessage message) {
        log.info("Sending message in {} - {} with content: {}!", messageContext.getInstance().getKey(), messageContext.getChannel().getName(), message.getText());
    }

}
