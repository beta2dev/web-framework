package ru.beta2.wf.flow;

/**
 * @author olegn 09.11.2014
 */
public interface Command
{

    CommandResolution execute(FlowContext ctx);

}
