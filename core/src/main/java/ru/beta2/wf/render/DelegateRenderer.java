package ru.beta2.wf.render;

import ru.beta2.wf.flow.FlowContext;
import ru.beta2.wf.model.Renderable;
import ru.beta2.wf.model.Renderer;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 21:56
 */
public abstract class DelegateRenderer implements Renderer
{

    private final Renderer delegate;

    public DelegateRenderer(Renderer delegate)
    {
        this.delegate = delegate;
    }

    protected void renderDelegate(Renderable<?> component, FlowContext ctx)
    {
        delegate.render(component, ctx);
    }

}
