package com.github.philippheuer.gcf4j.springboot.config;

import com.github.philippheuer.gcf4j.core.MessageHandlerRegistry;
import com.github.philippheuer.gcf4j.core.domain.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageHandlerRegistryConfig {

    @Autowired(required = false)
    private List<? extends MessageHandler> messageHandlers;

    @Bean
    public MessageHandlerRegistry getMessageHandlerRegistry() {
        MessageHandlerRegistry messageHandlerRegistry = new MessageHandlerRegistry();

        if (messageHandlers != null) {
            messageHandlers.forEach(handler -> messageHandlerRegistry.register(handler));
        }

        return messageHandlerRegistry;
    }

}
