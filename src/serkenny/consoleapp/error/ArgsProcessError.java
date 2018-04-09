package serkenny.consoleapp.error;

public class ArgsProcessError extends CommandError {

    public ArgsProcessError(String argument, String reason) {
        super(String.format("Argument %s: %s", argument, reason));
    }
}