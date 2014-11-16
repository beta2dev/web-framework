package ru.beta2.wf.model.build;

import ru.beta2.wf.model.flow.Command;

import java.util.List;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 20:40
 */
public interface BuildResult
{
    List<String> getWarnings();

    List<String> getErrors();

    boolean isPassed();

    List<Command> getCommands();
}
