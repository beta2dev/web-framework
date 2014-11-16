package ru.beta2.wf.model.component.generics;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.render.RenderContext;

public class Component<M> implements Renderable<M>
{

    private Renderer<M, ? extends Component<M>> renderer;

    public Renderer<M, ? extends Renderable<M>> getRenderer()
    {
        return renderer;
    }

    public void setRenderer(Renderer<M, ? extends Component<M>> renderer)
    {
        this.renderer = renderer;
    }

    public Component<M> renderer(Renderer<M, ? extends Component<M>> renderer)
    {
        setRenderer(renderer);
        return this;
    }

    public Component<M> wrap(Renderer<M, ? extends Component<M>> renderer)
    {
        setRenderer(new ComponentWrappingRenderer<>(renderer));
        return this;
    }

    @Override
    public void render(RenderContext ctx)
    {
        ((Renderer<M, Component<M>>) renderer).render(this, ctx);
    }

    @Override
    public M getModel(FlowContext ctx)
    {
        return null;
    }

    static {

        class DummyModel {}
        class DummyComponent extends Component<DummyModel> {}

        Renderer<DummyModel, DummyComponent> r1 = new Renderer<DummyModel, DummyComponent>()
        {
            @Override
            public void render(DummyComponent component, RenderContext ctx)
            {
                // dummy
            }
        };

        DummyComponent c1 = new DummyComponent();
        c1.renderer(r1);

        Renderable<DummyModel> rr = new Renderable<DummyModel>()
        {
            @Override
            public DummyModel getModel(FlowContext ctx)
            {
                return null;
            }

            @Override
            public void render(RenderContext ctx)
            {

            }
        };
//        c1.getRenderer().render(rr, null);

    }
}
