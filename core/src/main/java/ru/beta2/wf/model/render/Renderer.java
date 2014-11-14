package ru.beta2.wf.model.render;

/**
 * 10.11.2014
 * olegn
 */
public interface Renderer<M>
{

    void render(Renderable<M> component, RenderContext ctx); // todo !!! replace with RenderContext

}
