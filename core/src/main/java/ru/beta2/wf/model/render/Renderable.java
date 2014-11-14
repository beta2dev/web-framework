package ru.beta2.wf.model.render;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.util.Attachable;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 23:24
 */
public interface Renderable<M> extends Attachable
{

    String getId();

    String getName();

    M getModel(FlowContext ctx);

    void render(RenderContext ctx);

}
