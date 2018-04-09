package serkenny.consoleapp.error;

public class NoSuchCommandError extends Exception {
    public NoSuchCommandError(String commandName) {
        super("No command named \"" + commandName + "\"");
    }
}
