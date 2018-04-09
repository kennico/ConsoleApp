package serkenny.consoleapp.test;

import serkenny.consoleapp.Console;
import serkenny.consoleapp.command.FixedArgsCommand;
import serkenny.consoleapp.command.OptionCommand;
import serkenny.consoleapp.command.VarArgsCommand;
import serkenny.consoleapp.error.ExecutionError;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


/**
 * Run java program from command line prompt created by IntelliJ
 * <p>
 * https://stackoverflow.com/questions/27108911/how-to-run-java-program-in-command-prompt-created-by-intellij
 */
public class Demo extends Console {

    /**
     * @return true if any error occurred; otherwise, false is returned
     */
    @Override
    protected boolean preLaunched() {
        boolean code = super.preLaunched();

        // command that takes no argument
        addCommand("hi", new FixedArgsCommand(0) {
            @Override
            protected void execute() throws ExecutionError {
                outputln("Hello, world!");
            }
        });

        // command that takes arbitrary number of arguments
        addCommand("echo", new VarArgsCommand() {
            @Override
            protected void execute() throws ExecutionError {
                ListIterator<String> varargs = getVarvargs();
                while (varargs.hasNext()) {
                    outputln(varargs.next());
                }
            }
        });

        // command that takes options and values as arguments
        List<String> options = Arrays.asList("-t", "-l");
        addCommand("hello", new OptionCommand(options, null) {
            @Override
            protected void execute() throws ExecutionError {
                // TODO handle arguments of unexpected type

                // Default values
                int length = 1, times = 1;

                if (containsOpt("-l")) {
                    length = Integer.parseInt(retrieveOpt("-l"));
                }
                if (containsOpt("-t"))
                    times = Integer.parseInt(retrieveOpt("-t"));

                String repeated = new String(new char[length]).replace('\0', 'h');
                for (int i = 0; i < times; i++) {
                    outputln(repeated + "ello!");
                }

            }
        });

        return code;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.setUserName("misaka10032");
        demo.setAppName("demo");
        demo.launch();
    }

}
