package ru.beta2.wf.model.build.stages;

import ru.beta2.wf.model.build.BuildContext;
import ru.beta2.wf.model.component.Action;
import ru.beta2.wf.model.component.Application;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.component.Page;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 22:20
 */
public class GenerateIdStage extends AbstractStage
{

    private final BuildContext ctx;

    public GenerateIdStage(BuildContext ctx)
    {
        this.ctx = ctx;
    }

    @Override
    public void visit(Application app)
    {
        // do nothing
    }

    @Override
    public void visit(Component<?> component)
    {
        generateId(component);
    }

    @Override
    public void visit(Page<?> page)
    {
        generateId(page);
    }

    @Override
    public void visit(Action<?> action)
    {
        generateId(action);
    }

    private void generateId(Component<?> component)
    {
        if (component.getId() == null) {
            component.setId(ctx.generateId());
        }
    }
}
