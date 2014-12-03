package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.Dispatch;
import ru.beta2.wf.model.flow.Dispatched;
import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;

import java.util.HashMap;
import java.util.Map;

/**
 * 09.11.2014
 * olegn
 */
public class Page<M> extends CompositeComponent<M> implements Dispatched
{

    // todo !!! реализовать возможность для случая с layout делать шаблоны непосредственно для страницы и в шаблоне уже указывать "layoutBindings"

    private Dispatch dispatch;

    private Layout<M> layout;

    private Map<String, Component<?>> layoutBindings;

    // todo !!! ??? on verify phase check that dispatch exists

    // todo !!! еще нам нужно как-то смоделировать, что при навигации между страницами только часть блоков по идее должна быть перерисована (то есть нужно ввести некую иерархию композиции страниц)
    public Page<M> layout(Layout<M> layout)
    {
        this.layout = layout;
        return this;
    }

    public Layout<?> getLayout()
    {
        return layout;
    }

    @Override
    public String getRenderName()
    {
        return layout != null ? layout.getRenderName() : super.getRenderName(); // todo ??? может быть вынести стратегию и ее два варианта: layout == null или layout != null
    }

    public void addChild(Component<?> component, String layoutSlot)
    {
        addChild(component);
        if (layoutBindings == null) {
            layoutBindings = new HashMap<>();
        }
        layoutBindings.put(layoutSlot, component);
    }

    @Override
    public Renderable<?> getComponent(String name)
    {
        Renderable<?> c;
        if (layoutBindings != null) {
            c = layoutBindings.get(name);
            if (c != null) {
                return c;
            }
        }
        if (layout != null) {
            c = layout.getComponent(name);
            if (c != null) {
                return c;
            }
        }
        return super.getComponent(name);
    }

    @Override
    public void render(RenderContext ctx)
    {
        if (layout != null) {
            render(layout.getRenderer(), ctx);
        }
        else {
            super.render(ctx);
        }
    }

    @Override
    public boolean isRendererRequired()
    {
        return layout == null && super.isRendererRequired();
    }

    @Override
    public M getModel(FlowContext ctx)
    {
        return layout != null ? layout.getModel(ctx) : super.getModel(ctx); // todo DEFFERED maybe implement models merge ability
    }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
        acceptChildren(visitor);
    }

    //
    //  Dispatch
    //

    @Override
    public Dispatch getDispatch()
    {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch)
    {
        this.dispatch = dispatch;
    }

    public Page<M> dispatch(Dispatch dispatch)
    {
        setDispatch(dispatch);
        return this;
    }

    public Page<M> dispatch(String pathTemplate)
    {
        setDispatch(Dispatch.pathTemplate(pathTemplate));
        return this;
    }

    //
    //  Links
    //

    /**
     * Ссылка на страницу
     * todo !!! ??? а что если страница "не существует" без параметров? (например, карточка товаров) - то есть в произвольном случае нам надо предоставлять параметры страницы (параметры страницы и ее состояние - это разные вещи)
     * @return
     */
    public String getLink()
    {
        return dispatch.as(Dispatch.PathTemplate.class).get(); // todo !!! check if pathTemplate is not set
    }

    //
    // Override for type refinement
    //

    @Override
    public Page<M> renderer(Renderer renderer)
    {
        super.renderer(renderer);
        return this;
    }

    @Override
    public Page<M> name(String name)
    {
        super.name(name);
        return this;
    }

    @Override
    public Page<M> child(Component<?> component)
    {
        super.child(component);
        return this;
    }

}
