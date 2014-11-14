package ru.beta2.wf.model.build.stages;

import ru.beta2.wf.model.build.BuildContext;
import ru.beta2.wf.model.build.Buildable;
import ru.beta2.wf.model.component.Action;
import ru.beta2.wf.model.component.Application;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.component.Page;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 21:52
 */
public class BuildUpStage extends AbstractStage
{

    private final BuildContext ctx;

    public BuildUpStage(BuildContext ctx)
    {
        this.ctx = ctx;
    }

    @Override
    public void visit(Application app)
    {
        buildUp(app);
    }

    @Override
    public void visit(Component<?> component)
    {
        buildUp(component);
    }

    @Override
    public void visit(Page<?> page)
    {
        buildUp(page);
    }

    @Override
    public void visit(Action<?> action)
    {
        buildUp(action);
    }

    private void buildUp(Object obj)
    {
        if (obj instanceof Buildable) {
            ((Buildable) obj).buildUp(ctx);
        }
    }
}
