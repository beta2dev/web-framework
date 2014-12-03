package ru.beta2.wf.model.build.stages;

import ru.beta2.wf.model.build.BuildContext;
import ru.beta2.wf.model.component.Action;
import ru.beta2.wf.model.component.Application;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.component.Page;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 22:22
 */
public class AssignRendererStage extends AbstractStage
{

    private final BuildContext ctx;

    public AssignRendererStage(BuildContext ctx)
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
        assignRenderer(component);
    }

    @Override
    public void visit(Page<?> page)
    {
        assignRenderer(page);
    }

    @Override
    public void visit(Action<?> action)
    {
        assignRenderer(action);
    }

    private <M> void assignRenderer(Component<M> component)
    {
        if (component.isRendererRequired()) {
            Renderer<M, ? extends Renderable<M>> renderer = ctx.getRendererForComponent(component);
            if (renderer != null) {
                component.setRenderer(renderer);
            }
            else {
                ctx.addError("Renderer is not assigned for component: " + component);
            }
        }
    }

}
