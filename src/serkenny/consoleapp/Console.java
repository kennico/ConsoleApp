package serkenny.consoleapp;

import serkenny.consoleapp.command.Command;
import serkenny.consoleapp.error.ArgsProcessError;
import serkenny.consoleapp.error.CommandError;
import serkenny.consoleapp.error.ExecutionError;
import serkenny.consoleapp.error.NoSuchCommandError;

import java.util.*;


public class Console {

    private final static String USER_APP_FORMAT = "%s@%s:-> ";

    public enum STATUS {
        CREATED, INIT,
        RUNNING, TERMINATED
    }

    private STATUS status = STATUS.CREATED;
    private boolean shutdownFlag = false;

    private String userName = "serkenny";
    private String appName = "console";

    private Map<String, Command> cmdMap = new HashMap<>();

    protected boolean getShutdownFlag() {
        return shutdownFlag;
    }

    protected void setShutdownFlag(boolean shutdownFlag) {
        this.shutdownFlag = shutdownFlag;
    }


    public void launch() {
        setShutdownFlag(preLaunched());

        setStatus(STATUS.RUNNING);
        outputln("Console running...");

        Scanner scanner = new Scanner(System.in);

        while (!getShutdownFlag()) {
            prompt();

            try {
                // Read next line as a string
                String cmdLine = scanner.nextLine().trim();

                if (!cmdLine.equals("")) {

                    // split the string into a command name and arguments
                    List<String> rawArgs = new LinkedList<>(Arrays.asList(cmdLine.split("\\s+")));
                    String cmdName = rawArgs.remove(0);

                    try {
                        getCommand(cmdName).execute(rawArgs);

                    } catch (NoSuchCommandError e) {
                        outputln(e.getMessage());

                    } catch (CommandError e) {
                        outputln(String.format("Error in %s:\n\t%s", cmdName, e.getMessage()));

                    } catch (Exception e) {
                        outputln(e.getMessage());
                        setShutdownFlag(true);
                    }
                }

            } catch (NoSuchElementException e) {
                // catch the ctrl+c character
                setShutdownFlag(true);
            }
        }

        onTerminated();
    }

    /**
     * Terminates the current line by writing '\n'.
     */
    public void outputln() {
        output("\n");
    }

    /**
     * Print a formatted message and then terminate the line.
     *
     * @param format message format used by MessageFormat
     * @param args   variable length arguments
     */
    public void outputln(String format, Object... args) {
        output(format, args);
        outputln();
    }

    /**
     * Print a formatted message.
     *
     * @param format message format used by MessageFormat
     * @param args   variable length arguments
     */
    public void output(String format, Object... args) {
        System.out.format(format, args);
    }


    private void prompt() {
        output(String.format(USER_APP_FORMAT, getUserName(), getAppName()));
    }

    public STATUS getStatus() {
        return status;
    }

    private void setStatus(STATUS status) {
        this.status = status;
    }

    /**
     * @return true if any error occurs; otherwise, false is returned.
     */
    protected boolean preLaunched() {

        setStatus(STATUS.INIT);
        outputln("Console initializing...");

        addCommand("exit", new Command() {
            @Override
            public void execute(List<String> args) throws CommandError {
                setShutdownFlag(true);
            }

            @Override
            public void execute(String line) throws CommandError {
                setShutdownFlag(true);
            }
        });

        return false;
    }

    /**
     * Called on leaving.
     */
    protected void onTerminated() {
        setStatus(STATUS.TERMINATED);
        outputln("Console exited.");
    }

    protected void setUserName(String userName) {
        this.userName = userName;
    }

    protected void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAppName() {
        return appName;
    }

    protected Command getCommand(String commandName) throws NoSuchCommandError {
        Command command = cmdMap.get(commandName);
        if (command == null)
            throw new NoSuchCommandError(commandName);
        return command;
    }

    protected void addCommand(String commandName, Command command) {
        this.cmdMap.put(commandName, command);
    }

}
