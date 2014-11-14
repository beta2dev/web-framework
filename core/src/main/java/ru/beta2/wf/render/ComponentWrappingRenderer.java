package ru.beta2.wf.render;

import ru.beta2.wf.flow.FlowContext;
import ru.beta2.wf.model.Renderable;
import ru.beta2.wf.model.Renderer;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 21:59
 */
public class ComponentWrappingRenderer extends DelegateRenderer
{
    public ComponentWrappingRenderer(Renderer delegate)
    {
        super(delegate);
    }

    @Override
    public void render(Renderable<?> component, FlowContext ctx)
    {
        ctx.write("<div class=\"b2wrap\" data-wrap-id=\"" + component.getId() + "\">");
        renderDelegate(component, ctx);
        ctx.write("</div>");
    }
}
