package ru.beta2.wf.actions;

import ru.beta2.wf.components.TextContent;
import ru.beta2.wf.model.component.Action;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.render.Renderable;

/**
 * @author olegn 14.11.2014
 */
public abstract class AbstractLinkAction extends Action<LinkActionModel>
{

    private Component<?> content;

    public Component<?> getContent()
    {
        return content;
    }

    public void setContent(Component<?> content)
    {
        this.content = content;
    }

    public AbstractLinkAction content(String text)
    {
        setContent(new TextContent().text(text));
        return this;
    }

    public AbstractLinkAction content(Component<?> content)
    {
        setContent(content);
        return this;
    }

    @Override
    protected Class<? super LinkActionModel> getModelClass()
    {
        return LinkActionModel.class;
    }
}
