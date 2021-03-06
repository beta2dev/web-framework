package ru.beta2.wf.model.component;

import ru.beta2.wf.model.render.Renderer;

/**
 * todo ??? how to visit Layout objects (from what point) ?
 * User: Inc
 * Date: 12.11.2014
 * Time: 15:46
 */
public class Layout<M> extends CompositeComponent<M>
{

    // todo ??? нужны ли нам иерархические лэйауты ? (если да - то скорее всего нужно делать Layout базовым классом для Page и выносить туда функционал использования Layout'a

    //
    // Override for type refinement
    //

    @Override
    public Layout<M> renderer(Renderer renderer)
    {
        super.renderer(renderer);
        return this;
    }

    @Override
    public Layout<M> name(String name)
    {
        super.name(name);
        return this;
    }
}
