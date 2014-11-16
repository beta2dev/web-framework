package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.util.AbstractAttachable;

import java.util.Map;

/**
 * todo !!! refactore out this LayoutJoint
 * User: Inc
 * Date: 12.11.2014
 * Time: 23:17
 */                                 // todo !!! ??? create AbstractRenderable ?
public class LayoutJoint<M> extends AbstractAttachable implements Renderable<M>, HasChildren
{

    // todo !!! ??? maybe prebuild (when there is such lifecycle)

    private final Layout<M> layout;
    private final Map<String, Component<?>> layoutBindings;

    public LayoutJoint(Layout<M> layout, Map<String, Component<?>> layoutBindings)
    {
        this.layout = layout;
        this.layoutBindings = layoutBindings;
    }

    @Override
    public Renderable<?> getChildByName(String name)
    {
        Renderable<?> r = layoutBindings.get(name);
        if (r != null) {
            return r;
        }
        return layout.getChildByName(name);
    }

    @Override
    public String getId()
    {
        throw new RuntimeException("TODO");
//        return null; // todo !!! ??? что здесь
    }

    @Override
    public String getName()
    {
        return layout.getName();
    }

    @Override
    public M getModel(FlowContext ctx)
    {
        return layout.getModel(ctx);
    }

    @Override
    public void render(RenderContext ctx)
    {
        layout.getRenderer().render(this, ctx);
    }
}
