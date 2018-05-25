package serkenny.consoleapp.command;

import com.sun.istack.internal.Nullable;
import serkenny.consoleapp.error.ArgumentError;

import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class OptionCommand extends VarArgsCommand {

    private ArgsDispatcher dispatcher;
    private Map<String, String> kwargs;

    /**
     * @param options a List containing options, each of which corresponds to an actual argument
     * @param flags   a List containing flags
     */
    protected OptionCommand(@Nullable List<String> options, @Nullable List<String> flags) {
        this.dispatcher = new OptionDispatcher(options, flags);
    }

    public String retrieveOpt(String option) {
        return kwargs.get(option);
    }

    public boolean containsOpt(String option) {
        return kwargs.containsKey(option);
    }

    @Override
    protected void preprocessArgs() throws ArgumentError {
        OptionArgs args = dispatcher.parse(getRawArgStrings());
        setVarvargs(args.getVarargs().listIterator());
        this.kwargs = args.getKwargs();
    }

    public Set<Map.Entry<String, String>> getEntries() {
        return kwargs.entrySet();
    }
}
