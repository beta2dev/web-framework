package ru.beta2.wf.model.render;

import ru.beta2.wf.model.flow.FlowResolution;

/**
 * @author olegn 16.11.2014
 */
public class RenderResolution implements FlowResolution
{

    private final Renderable<?> renderable;

    public RenderResolution(Renderable<?> renderable)
    {
        this.renderable = renderable;
    }

    @Override
    public void execute(RenderContext ctx)
    {
        renderable.render(ctx);
    }
}
