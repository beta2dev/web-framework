package ru.beta2.wf.model.build;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 19:54
 */
public interface BuildLifecycle
{

    BuildStage getNextStage(BuildContext ctx);

}
