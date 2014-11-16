package ru.beta2.wf.actions;

import ru.beta2.wf.html.AbstractHtmlTagRenderer;
import ru.beta2.wf.html.HtmlTagWriter;
import ru.beta2.wf.model.render.RenderContext;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 14:46
 */
public class LinkActionRenderer extends AbstractHtmlTagRenderer<LinkActionModel, AbstractLinkAction>
{
    @Override
    public void render(AbstractLinkAction component, RenderContext ctx)
    {
        LinkActionModel model = component.getModel(ctx);
        HtmlTagWriter wr = getHtmlTagWriter(ctx);
        wr.start("a");

        // todo !!! need test
//        String href = model.getHref();
//        String dataHref = model.getActionHref();
//
//        if (href != null) {
//            wr.attr("href", href);
//            wr.attr("data-href", dataHref);
//        }
//        else {
//            wr.attr("href", href);
//        }

        wr.attr("href", model.getHref());
        wr.attr("data-href", model.getActionHref());
        wr.attr("data-action", component.getActionHandler());

        model.getContent().render(ctx);
        wr.end("a");
    }
}
