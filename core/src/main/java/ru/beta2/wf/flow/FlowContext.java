package ru.beta2.wf.flow;

import ru.beta2.wf.util.Attachable;

import java.util.Deque;
import java.util.Map;

/**
 * @author olegn 09.11.2014
 */
public interface FlowContext extends Attachable
{

    Map<String, Deque<String>> getQueryParameters();

    // todo !!! ??? write() and buffering скорее всего нужно переместить куда-то в render и сделать RenderContext (из которого будет доступен FlowContext или даже RenderContext extends FlowContext)

    void write(String s);

    OutputBuffer startBuffering();

}
