package ru.beta2.wf.model.component;

import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Inc
 * Date: 10.11.2014
 * Time: 17:10
 */
public class CompositeComponent<M> extends Component<M> implements HasChildren
{

    private List<Component<?>> children;

    public void addChild(Component<?> c)
    {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(c);
    }

    public void addChildren(Component<?>... cc)
    {
        for (Component<?> c : cc) {
            addChild(c);
        }
    }

    public CompositeComponent<M> child(Component<?> component)
    {
        addChild(component);
        return this;
    }

//    @Override
    public List<Component<?>> getChildren()
    {
        if (children == null) {
            return Collections.emptyList();
        }
        return children;
    }

    @Override
    public Component<?> getChildByName(String name)
    {
        // todo !!! ??? maybe optimize
        for (Component c : getChildren()) {
            if (name.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        super.accept(visitor);
        acceptChildren(visitor);
    }

    protected void acceptChildren(ComponentVisitor visitor)
    {
        for (Component<?> comp : getChildren()) {
            comp.accept(visitor);
        }
    }

    //
    //  Override for type refinement
    //

    @Override
    public CompositeComponent<M> renderer(Renderer<M, ? extends Renderable<M>> renderer)
    {
        super.renderer(renderer);
        return this;
    }

    @Override
    public CompositeComponent<M> name(String name)
    {
        super.name(name);
        return this;
    }
}
