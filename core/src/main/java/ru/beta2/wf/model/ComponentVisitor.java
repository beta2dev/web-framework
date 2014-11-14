package ru.beta2.wf.model;

/**
 * @author olegn 14.11.2014
 */
public interface ComponentVisitor
{

    void visit(Component<?> component);

    void visit(CompositeComponent<?> component);

    void visit(Page<?> page);

    void visit(Action<?> action);

}
