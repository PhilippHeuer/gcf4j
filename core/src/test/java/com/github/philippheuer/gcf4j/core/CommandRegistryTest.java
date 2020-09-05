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
                    ctx.getResponder().sendMessage(ctx, GCFMessage.builder().text("Hello World!").build());
                })
                .build();
        commandRegistry.register(ping);

        // call
        commandRegistry.call(getCommandContext());
    }

    private GCFMessageContext getCommandContext() {
        DummyResponder dummyResponder = new DummyResponder();

        GCFInstance messageInstance = GCFInstance.builder().type("test").id("1").build();
        GCFChannel commandChannel = GCFChannel.builder().id("general").name("General").build();
        GCFMember commandAuthor = GCFMember.builder().id("1").name("TestUser").bot(true).roles(Set.of(GCFRole.builder().name("admin").build())).build();
        GCFMessage message = GCFMessage.builder().text("Hello World!").command("ping").build();
        GCFMessageContext commandContext = new GCFMessageContext(messageInstance, commandChannel, commandAuthor, message, dummyResponder);

        return commandContext;
    }
}
