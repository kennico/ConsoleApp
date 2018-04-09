package serkenny.consoleapp.command;

import serkenny.consoleapp.error.CommandError;

import java.util.List;

public interface Command {
    /**
     * @param args list of raw argument strings
     * @throws CommandError e
     */
    void execute(List<String> args) throws CommandError;

    /**
     * @param line string containing raw arguments separated by whitespaces
     * @throws CommandError e
     */
    void execute(String line) throws CommandError;
}



