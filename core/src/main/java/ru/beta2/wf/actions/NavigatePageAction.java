package ru.beta2.wf.actions;

import ru.beta2.wf.components.TextContent;
import ru.beta2.wf.model.flow.CommandResolution;
import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.component.Page;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.component.Supplier;

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

    public NavigatePageAction page(Page page)
    {
        return page(Supplier.value(page));
    }

    public NavigatePageAction page(Supplier<Page> pageSupplier)
    {
        this.pageSupplier = pageSupplier;
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
                return NavigatePageAction.this.getContent();
            }
        };
    }

    // todo !!! очень интересно у нас получается - с одной стороны страницу можно "достать" непосредственно по ссылке и вот через команду - но подождите, а команда тогда на какой URL будет привязана?
    // todo или получается, что 1) мы как-то всегда должны ходить на страницы через команды или 2) действие не всегда под собой имеет серверную команду (что верно, кстати)
    // todo или 3) команда навигации на страницу имеет свой специфический технический URL

    // todo можно сделать так, что будут вообще только одни команды, отображаемые на конкретные pathTemplate (но что делать со страницами по статусу (отдельная тема в билдрезалт) и с командами по техническим URL'ам (наверное все равно стоит их привести к виду /cmd/COMMAND-NAME-IDENTIFIER))

    @Override
    public CommandResolution execute(FlowContext ctx)
    {
        return null;// todo !!! implement
    }
}
