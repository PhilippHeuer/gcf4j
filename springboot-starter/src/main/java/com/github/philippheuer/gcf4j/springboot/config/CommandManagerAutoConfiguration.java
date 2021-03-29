package com.github.philippheuer.gcf4j.springboot.config;

import com.github.philippheuer.gcf4j.api.command.IGCFCommand;
import com.github.philippheuer.gcf4j.core.command.CommandManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandManagerAutoConfiguration {

    @Autowired(required = false)
    private List<? extends IGCFCommand> commands;

    @Bean
    public CommandManager getCommandManager() {
        CommandManager cmdManager = new CommandManager();

        if (commands != null) {
            commands.forEach(cmdManager::register);
        }

        return cmdManager;
    }

}
