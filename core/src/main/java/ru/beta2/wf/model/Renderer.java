package ru.beta2.wf.model;

import ru.beta2.wf.flow.FlowContext;

/**
 * 10.11.2014
 * olegn
 */
public interface Renderer
{

    void render(Renderable<?> component, FlowContext ctx);

}
