package ru.beta2.wf.model.flow;

import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.render.RenderResolution;

/**
 * @author olegn 16.11.2014
 */
public class DefaultFlowLifecycle implements FlowLifecycle
{
    @Override
    public FlowResolution flow(Component<?> component, FlowContext ctx)
    {
        return new RenderResolution(component);
    }
}
