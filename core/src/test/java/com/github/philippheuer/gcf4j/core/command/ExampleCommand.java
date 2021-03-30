package com.github.philippheuer.gcf4j.core.command;

import com.github.philippheuer.gcf4j.api.command.GCFCommandOptionType;
import com.github.philippheuer.gcf4j.api.command.IGCFCommandOption;
import com.github.philippheuer.gcf4j.api.command.IGCFUsageExample;
import com.github.philippheuer.gcf4j.api.domain.IGCFMessageContext;
import com.github.philippheuer.gcf4j.core.domain.GCFMessage;
import com.github.philippheuer.gcf4j.core.domain.GCFMessageContext;

import java.util.List;
import java.util.Set;

public class ExampleCommand extends GCFCommand {

    private static final IGCFCommandOption OPTION_SILENT = GCFCommandOption.builder()
            .shortOpt("k")
            .longOpt("key")
            .type(GCFCommandOptionType.FLAG)
            .hasArgument(true)
            .isRequired(true)
            .description("command.config.parameter.key")
            .build();

    private static final IGCFUsageExample EXAMPLE_SILENT = GCFUsageExample.builder()
            .command("example --silent")
            .description("will not print any response")
            .build();

    public ExampleCommand() {
        super("example", Set.of("exx"), "test", "a simple example command", List.of(OPTION_SILENT), List.of(EXAMPLE_SILENT));
    }

    public IGCFMessageContext onExecution(IGCFMessageContext ctx) {
        return GCFMessageContext.replaceMessage(ctx, GCFMessage.builder().text("helloworld").build());
    }

}
