package com.github.philippheuer.gcf4j.core;

import com.github.philippheuer.gcf4j.core.domain.*;
import com.github.philippheuer.gcf4j.core.util.DummyResponder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

@Slf4j
public class CommandRegistryTest {

    private static CommandRegistry commandRegistry;

    @BeforeAll
    public static void initialize() {
        commandRegistry = new CommandRegistry();
    }

    @Test
    public void test() {
        // register
        Command ping = Command.builder()
                .name("ping")
                .aliases(Set.of("p"))
                .onExecution(ctx -> {
                    // command code
                    ctx.getResponder().sendMessage(ctx, Message.builder().text("Hello World!").build());
                })
                .build();
        commandRegistry.register(ping);

        // call
        commandRegistry.call(getCommandContext());
    }

    private MessageContext getCommandContext() {
        DummyResponder dummyResponder = new DummyResponder();

        MessageInstance messageInstance = MessageInstance.builder().type("test").id("1").build();
        MessageChannel commandChannel = MessageChannel.builder().id("general").name("General").build();
        MessageAuthor commandAuthor = MessageAuthor.builder().id("1").name("TestUser").bot(true).roles(Set.of("admin")).build();
        Message message = Message.builder().text("Hello World!").command("ping").build();
        MessageContext commandContext = new MessageContext(messageInstance, commandChannel, commandAuthor, message, dummyResponder);

        return commandContext;
    }
}
