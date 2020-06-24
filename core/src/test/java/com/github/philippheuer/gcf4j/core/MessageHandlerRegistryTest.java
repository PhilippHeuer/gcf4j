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
        MessageHandler printLogHandler = MessageHandler.builder()
                .handler(ctx -> {
                    // command code
                    log.info("Logging Message: {}", ctx.getMessage().getText());
                })
                .build();
        messageHandlerRegistry.register(printLogHandler);

        // call
        messageHandlerRegistry.call(getMessageContext());
    }

    private MessageContext getMessageContext() {
        DummyResponder dummyResponder = new DummyResponder();

        MessageInstance messageInstance = new MessageInstance("test", "1");
        MessageChannel commandChannel = new MessageChannel("general", "General");
        MessageAuthor commandAuthor = new MessageAuthor("1", "TestUser", true, Set.of("admin"));
        Message message = Message.builder().text("Hello World!").build();
        MessageContext commandContext = new MessageContext(messageInstance, commandChannel, commandAuthor, message, dummyResponder);

        return commandContext;
    }
}
