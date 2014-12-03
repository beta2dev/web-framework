package ru.beta2.wf.model.render;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.util.Attachable;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 23:24
 */
public interface Renderable<M> extends Attachable
{

    String getRenderId();

    String getRenderName(); // todo ??? или может быть оставить здесь и getId(), а вместо getName() что-то типа getSkinName() ?

    M getModel(FlowContext ctx);

    Renderable<?> getComponent(String name);

    void render(RenderContext ctx);

}
