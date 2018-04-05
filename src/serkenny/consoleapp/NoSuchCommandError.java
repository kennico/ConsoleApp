package serkenny.consoleapp;

public class NoSuchCommandError extends Exception {
    public NoSuchCommandError(String commandName) {
        super("No command named \"" + commandName + "\"");
    }
}
