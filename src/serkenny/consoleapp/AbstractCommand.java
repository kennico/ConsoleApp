package serkenny.consoleapp;


import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommand implements Command {

    private ArgsDispatcher dispatcher;

    protected AbstractCommand(@Nullable ArgsDispatcher dispatcher) {
        setDispatcher(dispatcher);
    }

    @Override
    public void execute(List<String> commandLineArgs) throws ExecutionError {
        ArgsDispatcher parser = getDispatcher();
        Arguments parsed = null;
        //
        // If dispatcher is null, all arguments are passed as multiple arguments.
        //
        if (parser == null)
            parsed = new Arguments(commandLineArgs, new HashMap<>());
        else
            parsed = parser.parse(commandLineArgs);

        run(parsed.getArgs(), parsed.getKwargs());
    }

    @Override
    public void execute(String argsLine) throws ExecutionError {
        argsLine = argsLine.trim();
        execute(Arrays.asList(argsLine.split("\\s+")));
    }

    /**
     * Upon calling this method, it is guaranteed all arguments are parsed as strings correctly.
     *
     * @param args   variadic arguments
     * @param kwargs keyword arguments
     * @throws ExecutionError e
     */
    protected abstract void run(List<String> args, Map<String, String> kwargs) throws ExecutionError;

    private ArgsDispatcher getDispatcher() {
        return dispatcher;
    }

    private void setDispatcher(ArgsDispatcher parser) {
        this.dispatcher = parser;
    }


}
