package ru.beta2.wf.model.render;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 21:56
 */
public abstract class DelegateRenderer<M> implements Renderer<M>
{

    private final Renderer<M> delegate;

    public DelegateRenderer(Renderer<M> delegate)
    {
        this.delegate = delegate;
    }

    protected void renderDelegate(Renderable<M> component, RenderContext ctx)
    {
        delegate.render(component, ctx);
    }

}
