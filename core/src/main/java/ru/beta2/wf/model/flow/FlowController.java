package ru.beta2.wf.model.flow;

import ru.beta2.wf.model.render.RenderContext;

/**
 * @author olegn 16.11.2014
 */
public class FlowController
{

    public void run(Flow flow, RenderContext ctx)
    {
        FlowResolution reso = flow.execute(ctx);
        if (reso != null) {
            reso.execute(ctx);
        }
    }

}
