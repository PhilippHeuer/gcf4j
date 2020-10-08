package com.github.philippheuer.gcf4j.core.util;

import com.github.philippheuer.gcf4j.api.IMessageConnector;
import com.github.philippheuer.gcf4j.api.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Set;

@Slf4j
public class DummyResponder implements IMessageConnector {

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void sendMessage(IGCFMessageContext messageContext, IGCFMessage message) {
        log.info("Sending message in {} - {} with content: {}!", messageContext.getInstance().getKey(), messageContext.getChannel().getName(), message.getText());
    }

    @Override
    public void editMessage(IGCFMessageContext messageContext, IGCFMessage message) {

    }

    @Override
    public void sendPrivateMessage(IGCFInstance instance, IGCFMember recipient, IGCFMessage message) {

    }

    @Override
    public void deleteMessage(IGCFMessageContext messageContext, IGCFMessage message) {

    }

    @Override
    public void muteMember(IGCFInstance instance, IGCFMember member) {

    }

    @Override
    public void unmuteMember(IGCFInstance instance, IGCFMember member) {

    }

    @Override
    public void banMember(IGCFInstance instance, IGCFMember member, Duration duration, String reason) {

    }

}
