package ru.beta2.wf.model.render;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 21:59
 */
public class ComponentWrappingRenderer<M, C extends Renderable<M>> extends DelegateRenderer<M, C>
{
    public ComponentWrappingRenderer(Renderer<M, C> delegate)
    {
        super(delegate);
    }

    @Override
    public void render(C component, RenderContext ctx)
    {
        ctx.write("<div class=\"b2wrap\" data-wrap-id=\"" + component.getId() + "\">");
        renderDelegate(component, ctx);
        ctx.write("</div>");
    }

}
