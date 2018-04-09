package serkenny.consoleapp.command;

import serkenny.consoleapp.error.ArgsProcessError;

import java.util.ListIterator;

public abstract class VarargsCommand extends AbstractCommand {

    private ListIterator<String> varvargs;

    /**
     * Process raw argument strings in advance.
     *
     * @throws ArgsProcessError if any error occurred
     */
    @Override
    protected void preprocessArgs() throws ArgsProcessError {
        setVarvargs(getRawArgStrings().listIterator());
    }

    void setVarvargs(ListIterator<String> varvargs) {
        this.varvargs = varvargs;
    }


    public ListIterator<String> getVarvargs() {
        return varvargs;
    }
}
