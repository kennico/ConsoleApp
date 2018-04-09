package serkenny.consoleapp.command;

import serkenny.consoleapp.error.ArgumentError;

import java.util.ListIterator;

/**
 * Super class for commands that take an arbitrary number of arguments
 */
public abstract class VarArgsCommand extends AbstractCommand {

    private ListIterator<String> varvargs;

    /**
     * Process raw argument strings in advance.
     *
     * @throws ArgumentError if any error occurred
     */
    @Override
    protected void preprocessArgs() throws ArgumentError {
        setVarvargs(getRawArgStrings().listIterator());
    }

    void setVarvargs(ListIterator<String> varvargs) {
        this.varvargs = varvargs;
    }


    public ListIterator<String> getVarvargs() {
        return varvargs;
    }
}
