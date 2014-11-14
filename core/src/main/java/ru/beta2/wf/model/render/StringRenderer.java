package ru.beta2.wf.model.render;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 19:11
 */
public class StringRenderer implements Renderer<String>
{
    @Override
    public void render(Renderable<String> component, RenderContext ctx)
    {
        ctx.write(component.getModel(ctx));
    }
}
