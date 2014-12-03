package ru.beta2.wf.model.render;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 19:11
 */
public class StringRenderer implements Renderer<String, Renderable<String>>
{
    @Override
    public void render(Renderable<String> renderable, RenderContext ctx)
    {
        ctx.write(renderable.getModel(ctx));
    }
}
