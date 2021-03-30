package com.github.philippheuer.gcf4j.core.util;

import lombok.SneakyThrows;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Utilities to interpret command options
 */
public class OptionUtils {

    /**
     * This will parse cli args and make them available for command execution
     *
     * @param options Options
     * @param payload text
     * @return CommandLine
     */
    @SneakyThrows
    public static CommandLine parseCommandLineArgs(Options options, String payload) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, payload != null ? payload.split("\\s") : ArrayUtils.toArray());
        } catch (ParseException | NumberFormatException ex) {
            // syntax error
            cmd = parser.parse(options, ArrayUtils.toArray());
        }

        return cmd;
    }

}
