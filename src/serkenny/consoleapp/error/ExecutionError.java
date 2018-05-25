package serkenny.consoleapp.error;


public class ExecutionError extends CommandError {

    public ExecutionError(String reason) {
        super(reason);
    }

    public ExecutionError(String format, Object... objects) {
        this(String.format(format, objects));
    }
}
