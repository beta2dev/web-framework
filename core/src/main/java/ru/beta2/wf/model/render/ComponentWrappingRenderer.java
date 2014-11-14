package ru.beta2.wf.model.render;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 21:59
 */
public class ComponentWrappingRenderer<M> extends DelegateRenderer<M>
{
    public ComponentWrappingRenderer(Renderer<M> delegate)
    {
        super(delegate);
    }

    @Override
    public void render(Renderable<M> component, RenderContext ctx)
    {
        ctx.write("<div class=\"b2wrap\" data-wrap-id=\"" + component.getId() + "\">");
        renderDelegate(component, ctx);
        ctx.write("</div>");
    }
}
