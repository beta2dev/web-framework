package ru.beta2.wf.actions;

import ru.beta2.wf.model.Action;

/**
 * @author olegn 14.11.2014
 */
public abstract class AbstractLinkAction extends Action<LinkActionModel>
{
    @Override
    protected Class<? super LinkActionModel> getModelClass()
    {
        return LinkActionModel.class;
    }
}
