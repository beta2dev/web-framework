package ru.beta2.wf.model.build.stages;

import ru.beta2.wf.model.build.BuildContext;
import ru.beta2.wf.model.component.*;
import ru.beta2.wf.model.flow.Command;

/**
 * @author olegn 16.11.2014
 */
public class GatherCommandsStage extends AbstractStage
{

    private final BuildContext ctx;

    public GatherCommandsStage(BuildContext ctx)
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
        // do nothing
    }

    @Override
    public void visit(Page<?> page)
    {
        ctx.addCommand(new PageViewCommand(page, ctx.getFlowLifecycle()));
    }

    @Override
    public void visit(Action<?> action)
    {
        if (action instanceof Command) {
            ctx.addCommand((Command) action);
        }
    }
}
