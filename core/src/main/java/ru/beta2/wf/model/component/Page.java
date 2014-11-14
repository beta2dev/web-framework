package ru.beta2.wf.model.component;

import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 09.11.2014
 * olegn
 */
public class Page<M> extends CompositeComponent<M>
{

    private final Optional<String> pathTemplate;

    /**
     * Привязка страницы к конкретному статус-коду (при этом можем сделать еще с учетом pathTemplate).
     */
    private final Optional<Integer> statusCode;

    private LayoutJoint<?> layoutJoint;

    private Map<String, Component<?>> layoutBindings;

    // todo !!! on verify phase check that

    public Page()
    {
        this(Optional.empty(), Optional.empty()); // todo !!! refactore (??? and maybe not use optional ?)
    }

    // todo ??? maybe move into setter
    public Page(String pathTemplate)
    {
        this(Optional.empty(), Optional.of(pathTemplate));
    }

    // todo ??? maybe move into setter
    public Page(int statusCode)
    {
        this(Optional.of(statusCode), Optional.empty());
    }

    private Page(Optional<Integer> statusCode, Optional<String> pathTemplate)
    {
        this.statusCode = statusCode;
        this.pathTemplate = pathTemplate;
    }

    public Optional<String> getPathTemplate()
    {
        return pathTemplate;
    }

    public Optional<Integer> getStatusCode()
    {
        return statusCode;
    }

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
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
        acceptChildren(visitor);
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
        return pathTemplate.get(); // todo !!! check if pathTemplate is not set
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
