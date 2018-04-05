package serkenny.consoleapp;

import java.util.List;
import java.util.Map;

public class Arguments {

    private List<String> args;

    private Map<String, String> kwargs;

    public Arguments(List<String> args, Map<String, String> kwargs) {
        this.args = args;
        this.kwargs = kwargs;
    }

    public List<String> getArgs() {
        return args;
    }

    public Map<String, String> getKwargs() {
        return kwargs;
    }
}


