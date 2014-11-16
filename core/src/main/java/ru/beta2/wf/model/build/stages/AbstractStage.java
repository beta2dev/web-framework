package ru.beta2.wf.model.build.stages;

import ru.beta2.wf.model.build.BuildStage;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 21:52
 */
public abstract class AbstractStage implements BuildStage
{

    @Override
    public boolean isPassed()
    {
        return true;
    }
}
