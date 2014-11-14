package ru.beta2.wf.model;

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

    //
    //  Override for type refinement
    //

    @Override
    public CompositeComponent<M> renderer(Renderer renderer)
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