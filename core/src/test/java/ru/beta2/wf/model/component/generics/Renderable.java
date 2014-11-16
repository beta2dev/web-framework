package ru.beta2.wf.model.component.generics;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.render.RenderContext;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 23:24
 */
public interface Renderable<M>
{

    M getModel(FlowContext ctx);

    void render(RenderContext ctx);

}
