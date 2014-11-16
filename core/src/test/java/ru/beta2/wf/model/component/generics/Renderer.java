package ru.beta2.wf.model.component.generics;

import ru.beta2.wf.model.render.RenderContext;

/**
 * 10.11.2014
 * olegn
 */
public interface Renderer<M, C extends Renderable<M>>
{

    void render(C component, RenderContext ctx);

}
