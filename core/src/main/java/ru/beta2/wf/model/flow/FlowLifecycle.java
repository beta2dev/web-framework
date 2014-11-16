package ru.beta2.wf.model.flow;

import ru.beta2.wf.model.component.Component;

/**
 * @author olegn 16.11.2014
 */
public interface FlowLifecycle
{
    FlowResolution flow(Component<?> component, FlowContext ctx);
}
