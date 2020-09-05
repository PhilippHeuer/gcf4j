package com.github.philippheuer.gcf4j.core;

import com.github.philippheuer.gcf4j.core.domain.*;
import com.github.philippheuer.gcf4j.core.util.DummyResponder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

@Slf4j
public class MessageHandlerRegistryTest {

    private static MessageHandlerRegistry messageHandlerRegistry;

    @BeforeAll
    public static void initialize() {
        messageHandlerRegistry = new MessageHandlerRegistry();
    }

    @Test
    public void test() {
        // register
        GCFMessageHandler printLogHandler = GCFMessageHandler.builder()
                .handler(ctx -> {
                    // command code
                    log.info("Logging Message: {}", ctx.getMessage().getText());
                })
                .build();
        messageHandlerRegistry.register(printLogHandler);

        // call
        messageHandlerRegistry.call(getMessageContext());
    }

    private GCFMessageContext getMessageContext() {
        DummyResponder dummyResponder = new DummyResponder();

        GCFInstance messageInstance = new GCFInstance("test", "1");
        GCFChannel commandChannel = new GCFChannel("general", "General");
        GCFMember commandAuthor = GCFMember.builder().id("1").name("TestUser").bot(true).roles(Set.of(GCFRole.builder().name("admin").build())).build();
        GCFMessage message = GCFMessage.builder().text("Hello World!").build();
        GCFMessageContext commandContext = new GCFMessageContext(messageInstance, commandChannel, commandAuthor, message, dummyResponder);

        return commandContext;
    }
}
