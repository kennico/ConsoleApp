package serkenny.consoleapp.command;

import serkenny.consoleapp.error.ArgsProcessError;

import java.util.List;

public interface ArgsDispatcher {

    OptionArgs parse(List<String> rawArgs) throws ArgsProcessError;

}
