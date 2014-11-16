package ru.beta2.wf.model.component.generics;

import ru.beta2.wf.model.render.RenderContext;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 21:56
 */
public abstract class DelegateRenderer<M, C extends Renderable<M>> implements Renderer<M, C>
{

    private final Renderer<M, C> delegate;

    public DelegateRenderer(Renderer<M, C> delegate)
    {
        this.delegate = delegate;
    }

    protected void renderDelegate(C component, RenderContext ctx)
    {
        delegate.render(component, ctx);
    }

}
