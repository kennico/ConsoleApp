package serkenny.consoleapp;

import java.util.List;

public interface Command {
    /**
     * @param args list of raw argument strings
     * @throws ExecutionError
     */
    void execute(List<String> args) throws ExecutionError;

    /**
     * @param line string containing raw arguments separated by whitespaces
     * @throws ExecutionError
     */
    void execute(String line) throws ExecutionError;
}



