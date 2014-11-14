package ru.beta2.wf.model;

import ru.beta2.wf.flow.FlowContext;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 23:24
 */
public interface Renderable<M>
{

    String getId();

    String getName();

    M getModel(FlowContext ctx);

    void render(FlowContext ctx);

}
