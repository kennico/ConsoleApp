import serkenny.consoleapp.*;

import java.util.List;
import java.util.Map;

public class Demo extends Console{

    /**
     * @return 1 if any error occurs; otherwise, 0 is returned.
     */
    @Override
    protected int preLaunched() {
        int code = super.preLaunched();

        addCommand("hello", new OptionCommand(null, null) {
            @Override
            protected void run(List<String> args, Map<String, String> kwargs) throws ExecutionError {
                try {
                    int times = Integer.parseInt(args.remove(0));
                    for (int i = 0 ; i < times ; ++i){
                        output("Hello, world!");
                    }
                } catch (Exception e) {
                    throw new ExecutionError(e.getMessage());
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
