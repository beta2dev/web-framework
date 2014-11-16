package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.Command;
import ru.beta2.wf.model.flow.Dispatch;
import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.flow.FlowResolution;

/**
 * @author olegn 16.11.2014
 */
public class CommandAction<M extends ActionModel> extends Action<M> implements Command
{
    @Override
    public FlowResolution execute(FlowContext ctx)
    {
        return null;
    }

    @Override
    public Dispatch getDispatch()
    {
        return Dispatch.pathTemplate("/cmd/" + getId()); // todo ??? maybe change (or use Strategy for generate path templates for command actions)
    }

    @Override
    public String getActionHandler()
    {
        return "b2cmd";
    }
}
