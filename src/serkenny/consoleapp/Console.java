package serkenny.consoleapp;

import java.text.MessageFormat;
import java.util.*;


public class Console {

    private final static String ECHO_FORMAT = "%s@%s:-> %s\n";
    private final static String PROMPT_FORMAT = "%s@%s:-> %s";

    public enum STATUS {
        CREATED, INIT,
        RUNNING, TERMINATED
    }

    private STATUS status = STATUS.CREATED;
    private String userName = "serkenny";
    private String appName = "console";
    private Map<String, Command> cmdMap = new HashMap<>();


    public void launch() {

        setStatus(STATUS.INIT);
        output("Console initializing...");
        int shutdownFlag = preLaunched();
        setStatus(STATUS.RUNNING);
        output("Console running...");

        Scanner scanner = new Scanner(System.in);

        while (shutdownFlag == 0) {
            prompt("");

            try {
                // Read next line as a string
                String cmdLine = scanner.nextLine().trim();

                if (!cmdLine.equals("")) {

                    // split the string into a command name and arguments
                    List<String> rawArgs = new LinkedList<>(Arrays.asList(cmdLine.split("\\s+")));
                    String cmdName = rawArgs.remove(0);

                    try {
                        getCommand(cmdName).execute(rawArgs);

                    } catch (ExecutionError e) {
                        echo(MessageFormat.format("Error in {0}:{1}", cmdName, e.getMessage()));

                    } catch (NoSuchCommandError e) {
                        echo(e.getMessage());

                    } catch (Exception e) {
                        echo(e.getMessage());
                        shutdownFlag = 1;
                    }
                }
            } catch (NoSuchElementException e) {
                // catch the ctrl+c character
                shutdownFlag = 1;
            }
        }

        output("Console exited.");
        onTerminated();

        setStatus(STATUS.TERMINATED);
    }

    public void output(String format, Object... args) {
        System.out.println(MessageFormat.format(format, args));
    }

    public void input(String format, Object... args) {
        System.out.print(MessageFormat.format(format, args));
    }

    private void echo(String message) {
        System.out.format(
                ECHO_FORMAT, getUserName(), getAppName(), message
        );
    }

    private void prompt(String message) {
        System.out.format(
                PROMPT_FORMAT, getUserName(), getAppName(), message
        );
    }

    public STATUS getStatus() {
        return status;
    }

    private void setStatus(STATUS status) {
        this.status = status;
    }

    /**
     *
     * @return 1 if any error occurs; otherwise, 0 is returned.
     */
    protected int preLaunched() {
        return 0;
    }

    protected void onTerminated() {
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
