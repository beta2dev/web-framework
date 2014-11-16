package ru.beta2.wf.actions;

import ru.beta2.wf.model.component.ActionModel;
import ru.beta2.wf.model.render.Renderable;

/**
 * @author olegn 14.11.2014
 */
public interface LinkActionModel extends ActionModel
{
    String getHref();
    String getActionHref();
    Renderable<?> getContent();
}
