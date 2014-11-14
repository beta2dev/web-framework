package ru.beta2.wf.model.flow;

import ru.beta2.wf.model.render.OutputBuffer;
import ru.beta2.wf.util.Attachable;

import java.util.Deque;
import java.util.Map;

/**
 * @author olegn 09.11.2014
 */
public interface FlowContext extends Attachable
{

    Map<String, Deque<String>> getQueryParameters();

}
