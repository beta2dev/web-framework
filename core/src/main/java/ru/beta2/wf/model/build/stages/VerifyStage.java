package ru.beta2.wf.model.build.stages;

import ru.beta2.wf.model.build.BuildContext;
import ru.beta2.wf.model.build.Verifiable;
import ru.beta2.wf.model.component.Action;
import ru.beta2.wf.model.component.Application;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.component.Page;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 22:35
 */
public class VerifyStage extends AbstractStage
{

    private final BuildContext ctx;

    public VerifyStage(BuildContext ctx)
    {
        this.ctx = ctx;
    }

    @Override
    public boolean isPassed()
    {
        return !ctx.hasErrors();
    }

    @Override
    public void visit(Application app)
    {
        verify(app);
    }

    @Override
    public void visit(Component<?> component)
    {
        verify(component);
    }

    @Override
    public void visit(Page<?> page)
    {
        verify(page);
    }

    @Override
    public void visit(Action<?> action)
    {
        verify(action);
    }

    private void verify(Object obj)
    {
        if (obj instanceof Verifiable) {
            ((Verifiable) obj).verify(ctx);
        }
    }
}
