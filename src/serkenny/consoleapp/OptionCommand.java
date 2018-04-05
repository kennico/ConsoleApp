package serkenny.consoleapp;

import java.util.List;


public abstract class OptionCommand extends AbstractCommand {
    /**
     * @param options a List containing options, each of which corresponds to an actual argument
     * @param flags   a List containing flags
     */
    public OptionCommand(List<String> options, List<String> flags) {
        super(new OptionDispatcher(options, flags));
    }
}
