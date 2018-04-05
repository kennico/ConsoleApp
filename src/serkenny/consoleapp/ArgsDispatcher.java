package serkenny.consoleapp;

import java.util.List;

public interface ArgsDispatcher {

    Arguments parse(List<String> rawArgs) throws ExecutionError;

}
