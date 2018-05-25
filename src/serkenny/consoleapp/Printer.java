package serkenny.consoleapp;

public class Printer {
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
}
