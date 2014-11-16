package ru.beta2.wf.model.build;

import ru.beta2.wf.model.flow.Command;
import ru.beta2.wf.util.Attachable;

import java.util.List;

/**
 * @author olegn 14.11.2014
 */
public interface BuildContext extends BuildServices, Attachable
{

    void addWarning(String msg);

    List<String> getWarnings();

    void addError(String msg);

    List<String> getErrors();

    boolean hasErrors();

    void addCommand(Command cmd);

    // todo ??? !!! добавить сюда возможность узнать "текущую" страница

}
