package serkenny.consoleapp.command;

import serkenny.consoleapp.error.ArgumentError;

import java.util.List;

public interface ArgsDispatcher {

    OptionArgs parse(List<String> rawArgs) throws ArgumentError;

}
