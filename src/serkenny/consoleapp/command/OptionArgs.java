package serkenny.consoleapp.command;

import java.util.List;
import java.util.Map;

public class OptionArgs {

    private List<String> varargs;
    private Map<String, String> kwargs;

    OptionArgs(List<String> args, Map<String, String> kwargs) {
        this.varargs = args;
        this.kwargs = kwargs;
    }

    List<String> getVarargs() {
        return varargs;
    }

    Map<String, String> getKwargs() {
        return kwargs;
    }
}
