package ru.beta2.wf.tdd;

import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.component.CompositeComponent;
import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;

/**
 * User: Inc
 * Date: 10.11.2014
 * Time: 17:39
 */
public class ComponentsRenderer implements Renderer
{

    private final String name;

    public ComponentsRenderer(String name)
    {
        this.name = name;
    }

    @Override
    public void render(Renderable renderable, RenderContext ctx)
    {
        ctx.write("<" + name + ">");
        if (renderable instanceof CompositeComponent) {
            for (Component c : ((CompositeComponent<?>) renderable).getChildren()) {
                c.render(ctx);
            }
        }
        ctx.write("</" + name + ">");
    }
}
