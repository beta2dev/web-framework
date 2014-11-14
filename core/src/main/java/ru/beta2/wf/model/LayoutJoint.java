package ru.beta2.wf.model;

import ru.beta2.wf.flow.FlowContext;

import java.util.Map;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 23:17
 */
public class LayoutJoint<M> implements Renderable<M>, HasChildren
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
    public void render(FlowContext ctx)
    {
        layout.getRenderer().render(this, ctx);
    }
}
