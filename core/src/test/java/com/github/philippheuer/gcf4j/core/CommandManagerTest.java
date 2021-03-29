package com.github.philippheuer.gcf4j.core;

import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.core.command.CommandManager;
import com.github.philippheuer.gcf4j.core.command.ExampleCommand;
import com.github.philippheuer.gcf4j.core.util.MessageContextTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

@Slf4j
public class CommandManagerTest {

    @Test
    void testRegisterCommand() {
        CommandManager commandManager = new CommandManager();
        commandManager.register(new ExampleCommand());

        Assertions.assertEquals(commandManager.getCommands().size(), 1);
    }

    @Test
    void testParseMessage() {
        CommandManager commandManager = new CommandManager();
        commandManager.register(new ExampleCommand());

        IGCFMessageContext ctx = MessageContextTestUtils.getMessageWithFakeContext("!example --silent");

        var containsCommand = commandManager.parseMessage(ctx, Set.of("!"));

        Assertions.assertTrue(containsCommand);
        Assertions.assertEquals(ctx.getMessage().getCommand(), "example");
        Assertions.assertEquals(ctx.getMessage().getCommandPayload(), "--silent");

        commandManager.runCommand(ctx);
    }

}
