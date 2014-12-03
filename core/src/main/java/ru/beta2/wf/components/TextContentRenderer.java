package ru.beta2.wf.components;

import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderer;

/**
 * User: Inc
 * Date: 03.12.2014
 * Time: 22:20
 */
public class TextContentRenderer implements Renderer<String, TextContent>
{
    @Override
    public void render(TextContent renderable, RenderContext ctx)
    {
        ctx.write(renderable.getModel(ctx));
    }
}
