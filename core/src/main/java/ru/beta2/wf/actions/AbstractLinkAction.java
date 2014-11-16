package ru.beta2.wf.actions;

import ru.beta2.wf.components.TextContent;
import ru.beta2.wf.model.component.CommandAction;
import ru.beta2.wf.model.component.Component;

/**
 * @author olegn 14.11.2014
 */
public abstract class AbstractLinkAction extends CommandAction<LinkActionModel>
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
    public Class<? super LinkActionModel> getModelClass()
    {
        return LinkActionModel.class;
    }
}
