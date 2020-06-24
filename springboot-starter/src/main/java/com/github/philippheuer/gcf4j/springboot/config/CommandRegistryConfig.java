package com.github.philippheuer.gcf4j.springboot.config;

import com.github.philippheuer.gcf4j.core.CommandRegistry;
import com.github.philippheuer.gcf4j.core.domain.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CommandRegistryConfig {

    @Autowired(required = false)
    private List<? extends Command> commandList;

    @Bean
    public CommandRegistry getCommandRegistry() {
        CommandRegistry commandRegistry = new CommandRegistry();

        if (commandList != null) {
            commandList.forEach(command -> {
                log.info("Registering command [{}]", command.getName());
                commandRegistry.register(command);
            });
        }

        return commandRegistry;
    }

}
