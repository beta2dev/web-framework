package ru.beta2.wf.model.build;

import ru.beta2.wf.model.component.ComponentVisitor;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 20:49
 */
public interface BuildStage extends ComponentVisitor
{

    boolean isPassed();

}
