package ru.beta2.wf.actions;

import ru.beta2.wf.components.TextContent;
import ru.beta2.wf.flow.CommandResolution;
import ru.beta2.wf.flow.FlowContext;
import ru.beta2.wf.model.*;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 22:45
 *
 * Render example:
 * <a href="/second-page" id="go-second-page" title="Go second page" class="b2action" data-action="b2nav">go</a>
 *      href - from model
 *      id - from attached attributes model - по идее нам нужно прокидывать это в Action.Model - вообще это надо renderer настраивать (?)
 *      title - from attached attributes (? or some ActionInfo model) model
 *      class - in renderer
 *      data-action - from action (or from model ?)
 *      go - ??? (component or some ActionInfo model) - ??? in the case of component renderer should get it from HasContent.getContent()
 *          how to choose - use component or ActionInfo (or something else ?)
 *
 *      todo ??? what about enabled/disabled state
 */
public class NavigatePageAction extends AbstractLinkAction
{

    // todo !!! include in component lifecycle verification stage (здесь например проверять, что задана страница

    private Supplier<Page> pageSupplier;

    private Renderable<?> content;

    public NavigatePageAction page(Page page)
    {
        return page(Supplier.value(page));
    }

    public NavigatePageAction page(Supplier<Page> pageSupplier)
    {
        this.pageSupplier = pageSupplier;
        return this;
    }

    public NavigatePageAction content(String text)
    {
        content = new TextContent().text(text);
        return this;
    }

    // todo !!! set page parameters (static and supplier) - how to correlate page and page model types ?


    @Override
    protected LinkActionModel createModel(FlowContext ctx)
    {
        return new LinkActionModel()
        {
            @Override
            public String getHref()
            {
                return pageSupplier.get(ctx).getLink();
            }

            @Override
            public String getAction()
            {
                return "b2nav";
            }

            @Override
            public Renderable<?> getContent()
            {
                return content;
            }
        };
    }

    @Override
    public CommandResolution execute(FlowContext ctx)
    {
        return null;// todo !!! implement
    }
}
