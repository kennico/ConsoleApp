package serkenny.consoleapp.error;

public class NoSuchCommand extends Exception {
    public NoSuchCommand(String commandName) {
        super("No command named \"" + commandName + "\"");
    }
}
