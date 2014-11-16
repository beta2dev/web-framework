package ru.beta2.wf.model.component.generics;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.render.RenderContext;

public class Component2<M> implements Renderable<M>
{

    private Renderer<M, ? super Component2<M>> renderer;

    public Renderer<M, ? super Component2<M>> getRenderer()
    {
        return renderer;
    }

    public void setRenderer(Renderer<M, ? super Component2<M>> renderer)
    {
        this.renderer = renderer;
    }

    public Component2<M> renderer(Renderer<M, ? super Component2<M>> renderer)
    {
        setRenderer(renderer);
        return this;
    }

    public Component2<M> wrap(Renderer<M, ? super Component2<M>> renderer)
    {
//        setRenderer(new ComponentWrappingRenderer2<>(renderer));
        return this;
    }

    @Override
    public void render(RenderContext ctx)
    {
        renderer.render(this, ctx);
    }

    @Override
    public M getModel(FlowContext ctx)
    {
        return null;
    }

    static {

        class DummyModel {}
        class DummyComponent extends Component2<DummyModel>
        {
            @Override
            public Component2<DummyModel> renderer(Renderer<DummyModel, ? super Component2<DummyModel>> renderer)
            {
                return super.renderer(renderer);
            }
        }

        Renderer<DummyModel, DummyComponent> r1 = new Renderer<DummyModel, DummyComponent>()
        {
            @Override
            public void render(DummyComponent component, RenderContext ctx)
            {
                // dummy
            }
        };

        DummyComponent c1 = new DummyComponent();
//        c1.renderer(r1);

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
