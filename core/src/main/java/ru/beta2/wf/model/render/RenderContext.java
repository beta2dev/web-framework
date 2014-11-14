package ru.beta2.wf.model.render;

import ru.beta2.wf.model.flow.FlowContext;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 14:53
 */
public interface RenderContext extends FlowContext
{

    void write(String s);

    OutputBuffer startBuffering();

}
