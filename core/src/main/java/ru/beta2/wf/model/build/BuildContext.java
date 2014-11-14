package ru.beta2.wf.model.build;

import ru.beta2.wf.model.flow.Command;

/**
 * todo !!! inline BuildResultsCollector here
 * @author olegn 14.11.2014
 */
public interface BuildContext extends BuildServices
{

    void registerCommand(Command cmd);

    // todo !!! добавить сюда возможность узнать "текущую" страница

}
