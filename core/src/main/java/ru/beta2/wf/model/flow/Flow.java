package ru.beta2.wf.model.flow;

/**
 * @author olegn 16.11.2014
 */
public interface Flow
{
    FlowResolution execute(FlowContext ctx);
}
