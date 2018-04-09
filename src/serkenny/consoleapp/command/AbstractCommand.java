package serkenny.consoleapp.command;


import serkenny.consoleapp.error.*;

import java.util.*;

public abstract class AbstractCommand implements Command {

    private List<String> rawArgStrings = null;

    /**
     * Process raw argument strings in advance.
     *
     * @throws ArgsProcessError if any error occurred
     */
    protected abstract void preprocessArgs() throws ArgsProcessError;

    /**
     * Shall not be overridden
     *
     * @param commandLineArgs list of strings
     * @throws ExecutionError e
     */
    @Override
    public void execute(List<String> commandLineArgs) throws CommandError {
        this.rawArgStrings = commandLineArgs;
        preprocessArgs();
        execute();
    }

    /**
     * Shall not be overridden
     *
     * @param argsLine string arguments in one line
     * @throws ExecutionError e
     */
    @Override
    public void execute(String argsLine) throws CommandError {
        argsLine = argsLine.trim();
        execute(Arrays.asList(argsLine.split("\\s+")));
    }

    /**
     * @throws ExecutionError e
     */
    protected abstract void execute() throws ExecutionError;

    /**
     * Access the original argument strings
     *
     * @return list of argument strings
     */
    protected List<String> getRawArgStrings() {
        return rawArgStrings;
    }

}
