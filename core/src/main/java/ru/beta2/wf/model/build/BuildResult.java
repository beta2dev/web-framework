package ru.beta2.wf.model.build;

import ru.beta2.wf.model.flow.Command;
import ru.beta2.wf.util.Attachable;

import java.util.List;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 20:40
 */
public interface BuildResult extends Attachable
{
    List<Command> getCommands();
}
