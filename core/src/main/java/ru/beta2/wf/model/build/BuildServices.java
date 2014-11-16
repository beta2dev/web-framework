package ru.beta2.wf.model.build;

import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.flow.FlowLifecycle;
import ru.beta2.wf.model.render.Renderer;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 22:39
 */
public interface BuildServices
{
    String generateId();

    <M, C extends Component<M>> Renderer<M, C> getRendererForComponent(C component);

    FlowLifecycle getFlowLifecycle();

}
