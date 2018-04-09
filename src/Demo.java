import serkenny.consoleapp.Console;
import serkenny.consoleapp.command.OptionCommand;
import serkenny.consoleapp.error.ExecutionError;


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

        addCommand("hello", new OptionCommand(null, null) {

            /**
             * @throws ExecutionError e
             */
            @Override
            protected void execute() throws ExecutionError {
                int times = Integer.parseInt(getVarvargs().next());
                for (int i = 0; i < times; i++) {
                    outputln("Hello, world!");
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
