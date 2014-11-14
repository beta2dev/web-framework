package ru.beta2.wf.model.component;

/**
 * @author olegn 14.11.2014
 */
public interface ComponentVisitor
{

    void visit(Application app);

    void visit(Component<?> component);

    void visit(Page<?> page);

    void visit(Action<?> action);

}
