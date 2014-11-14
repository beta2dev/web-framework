package ru.beta2.wf.model.component;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 19:34
 */
public interface Visitable
{

    void accept(ComponentVisitor visitor);

}
