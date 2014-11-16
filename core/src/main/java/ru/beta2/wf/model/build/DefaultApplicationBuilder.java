package ru.beta2.wf.model.build;

import org.apache.commons.lang3.RandomStringUtils;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.flow.DefaultFlowLifecycle;
import ru.beta2.wf.model.flow.FlowLifecycle;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author olegn 16.11.2014
 */
public class DefaultApplicationBuilder extends ApplicationBuilder
{

    private int idLength = 20;
    private FlowLifecycle flowLifecycle = new DefaultFlowLifecycle();

    private final Map<Class, Renderer<?, ?>> componentRenderers = new HashMap<>();
    private final Map<Class, Renderer<?, ?>> modelRenderers = new HashMap<>();

    public DefaultApplicationBuilder flowLifecycle(FlowLifecycle lifecycle)
    {
        this.flowLifecycle = lifecycle;
        return this;
    }

    public DefaultApplicationBuilder idLength(int value)
    {
        this.idLength = value;
        return this;
    }

    public <M, C extends Renderable<M>> DefaultApplicationBuilder registerRendererForComponent(Class<C> componentClass, Renderer<M, C> renderer)
    {
        componentRenderers.put(componentClass, renderer);
        return this;
    }

    public <M> DefaultApplicationBuilder registerRendererForModel(Class<M> modelClass, Renderer<M, ?> renderer)
    {
        modelRenderers.put(modelClass, renderer);
        return this;
    }

    //
    //  Implementation
    //

    @Override
    protected BuildContextImpl createBuildContext()
    {
        return new DefaultBuildContextImpl();
    }

    private class DefaultBuildContextImpl extends BuildContextImpl
    {

        @Override
        public String generateId()
        {
            return "ID" + RandomStringUtils.randomAlphanumeric(idLength - 2);
        }

        @Override
        public <M, C extends Component<M>> Renderer<M, C> getRendererForComponent(C component)
        {
            Renderer<?, ?> renderer = componentRenderers.get(component.getClass());
            if (renderer == null) {
                renderer = modelRenderers.get(component.getModelClass());
                if (renderer == null) {
                    return null;
                }
            }
            // hack
            return (Renderer<M, C>) renderer;
        }

        @Override
        public FlowLifecycle getFlowLifecycle()
        {
            return flowLifecycle;
        }
    }
}
