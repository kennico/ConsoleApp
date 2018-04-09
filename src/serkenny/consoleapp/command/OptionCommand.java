package serkenny.consoleapp.command;

import com.sun.istack.internal.Nullable;
import serkenny.consoleapp.error.ArgsProcessError;

import java.util.List;
import java.util.Map;


public abstract class OptionCommand extends VarargsCommand {

    private ArgsDispatcher dispatcher;
    private Map<String, String> kwargs;

    /**
     * @param options a List containing options, each of which corresponds to an actual argument
     * @param flags   a List containing flags
     */
    protected OptionCommand(@Nullable List<String> options, @Nullable List<String> flags) {
        this.dispatcher = new OptionDispatcher(options, flags);
    }

    public String getOption(String option) {
        return kwargs.get(option);
    }

    @Override
    protected void preprocessArgs() throws ArgsProcessError {
        OptionArgs args = dispatcher.parse(getRawArgStrings());
        setVarvargs(args.getVarargs().listIterator());
        this.kwargs = args.getKwargs();
    }
}
