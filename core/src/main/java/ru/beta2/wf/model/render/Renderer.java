package ru.beta2.wf.model.render;

/**
 * 10.11.2014
 * olegn
 */
public interface Renderer<M, C extends Renderable<M>>
{

    void render(C renderable, RenderContext ctx);

}
