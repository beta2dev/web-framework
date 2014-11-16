package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.*;

/**
 * @author olegn 16.11.2014
 */
public class PageViewCommand implements Command
{

    private final Page<?> page;
    private final FlowLifecycle flowLifecycle;

    public PageViewCommand(Page<?> page, FlowLifecycle flowLifecycle)
    {
        this.page = page;
        this.flowLifecycle = flowLifecycle;
    }

    @Override
    public Dispatch getDispatch()
    {
        return page.getDispatch();
    }

    @Override
    public FlowResolution execute(FlowContext ctx)
    {
        return flowLifecycle.flow(page, ctx);
    }
}
