package serkenny.consoleapp.error;

public class ArgumentError extends CommandError {

    public ArgumentError(String argument, String reason) {
        super(String.format("%s: %s", argument, reason));
    }
}