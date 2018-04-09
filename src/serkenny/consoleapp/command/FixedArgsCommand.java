package serkenny.consoleapp.command;

import serkenny.consoleapp.error.ArgumentError;

/**
 * Super class for commands that take a fixed number of arguments
 */
public abstract class FixedArgsCommand extends VarArgsCommand {
    private int paramNumber;

    public FixedArgsCommand(int paramNumber) {
        this.paramNumber = paramNumber;
    }

    /**
     * Process raw argument strings in advance.
     *
     * @throws ArgumentError if any error occurred
     */
    @Override
    protected void preprocessArgs() throws ArgumentError {
        int actualNumber = getRawArgStrings().size();
        if (actualNumber != paramNumber) {
            throw new ArgumentError(
                    String.format("Command only takes %d parameter(s)", paramNumber),
                    String.format("%d argument(s) found.", actualNumber)
            );
        }
        super.preprocessArgs();
    }
}
