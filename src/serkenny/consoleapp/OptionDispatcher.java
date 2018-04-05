package serkenny.consoleapp;

import com.sun.istack.internal.Nullable;

import java.text.MessageFormat;
import java.util.*;

public class OptionDispatcher implements ArgsDispatcher {

    private Set<String> OPTIONS = new HashSet<>();
    private Set<String> FLAGS = new HashSet<>();

    /**
     * Both <b>options</b> and <b>flags</b> are characters prefixed by a dash.
     * Each string of <b>options</b> corresponds to an actual argument while the one in <b>flags</b> doesn't.
     * For example, the string pair "-f" "C:/Windows" is considered as an option and its argument.
     * <b>options</b> and <b>flags</b> can be grouped together. Following argument strings will be dispatched
     * to options in the order of their appearance.
     *
     * @param options collection of strings considered as options
     * @param flags   collection of strings considered as flags
     */
    public OptionDispatcher(@Nullable Collection<String> options, @Nullable Collection<String> flags) {
        if (options != null)
            this.OPTIONS.addAll(options);
        if (flags != null)
            this.FLAGS.addAll(flags);
    }

    @Override
    public Arguments parse(List<String> rawArgs) throws ExecutionError {

        List<String> pendingFlags = new LinkedList<>();

        List<String> args = new LinkedList<>();
        Map<String, String> kwargs = new HashMap<>();

        for (String rawArg : rawArgs) {
            if (rawArg.startsWith("-")) {
                /*

                If next string starts with a dash, then a potential flag is detected

                */
                for (int i = 1; i < rawArg.length(); i++) {
                    String flag = "-" + rawArg.charAt(i);

                    if (OPTIONS.contains(flag)) {
                        pendingFlags.add(flag);
                    } else if (FLAGS.contains(flag)) {
                        kwargs.put(flag, null);
                    } else {
                        throw new ExecutionError("Unknown option: \"" + flag + "\"");
                    }
                }

            } else if (!pendingFlags.isEmpty()) {
                /*

                If it is not prefixed by a dash and there exists flags that
                has been detected but not been paired with an actual argument.

                */
                String flag = pendingFlags.remove(0);
                kwargs.put(flag, rawArg);
            } else {
                /*
                Otherwise, it is considered as one the variable length arguments.
                */
                args.add(rawArg);
            }
        }
        /*
        If all raw strings from command line have been checked
        while there still exists flag(s) not paired with argument(s).
        */
        if (!pendingFlags.isEmpty()) {
            String message = MessageFormat.format(
                    "Missing arguments for \"{0}\"",
                    String.join("\",\"", pendingFlags)
            );
            throw new ExecutionError(message);
        }
        return new Arguments(args, kwargs);
    }
}
