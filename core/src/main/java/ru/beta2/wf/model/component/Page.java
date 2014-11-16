package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.Dispatch;
import ru.beta2.wf.model.flow.Dispatched;
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

    private Dispatch dispatch;

    private LayoutJoint<?> layoutJoint;

    private Map<String, Component<?>> layoutBindings;

    // todo !!! on verify phase check that dispatch exists



    // todo !!! еще нам нужно как-то смоделировать, что при навигации между страницами только часть блоков по идее должна быть перерисована (то есть нужно ввести некую иерархию композиции страниц)
    public Page<M> layout(Layout<?> layout)
    {
        if (layoutBindings == null) {
            layoutBindings = new HashMap<>();
        }
        this.layoutJoint = new LayoutJoint<>(layout, layoutBindings);
        return this;
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
    public void render(RenderContext ctx)
    {
        if (layoutJoint != null) {
            layoutJoint.render(ctx);
        }
        else {
            super.render(ctx);
        }
    }

    @Override
    public Renderer<M, Renderable<M>> getRenderer()
    {
        if (layoutJoint != null) {
            return layoutJoint.render(ctx);
        }
        else {
            super.render(ctx);
        }
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
