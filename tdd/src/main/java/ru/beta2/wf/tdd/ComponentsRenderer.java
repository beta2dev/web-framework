package ru.beta2.wf.tdd;

import io.undertow.io.Sender;
import ru.beta2.wf.flow.FlowContext;
import ru.beta2.wf.model.Component;
import ru.beta2.wf.model.CompositeComponent;
import ru.beta2.wf.model.Renderable;
import ru.beta2.wf.model.Renderer;

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
    public void render(Renderable component, FlowContext ctx)
    {
        ctx.write("<" + name + ">");
        if (component instanceof CompositeComponent) {
            for (Component c : ((CompositeComponent<?>) component).getChildren()) {
                c.render(ctx);
            }
        }
        ctx.write("</" + name + ">");
    }
}
