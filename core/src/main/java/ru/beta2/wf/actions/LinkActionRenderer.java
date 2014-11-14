package ru.beta2.wf.actions;

import ru.beta2.wf.html.AbstractHtmlTagRenderer;
import ru.beta2.wf.html.HtmlTagWriter;
import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderable;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 14:46
 */
public class LinkActionRenderer extends AbstractHtmlTagRenderer<LinkActionModel>
{
    @Override
    public void render(Renderable<LinkActionModel> component, RenderContext ctx)
    {
        LinkActionModel model = component.getModel(ctx);
        HtmlTagWriter wr = getHtmlTagWriter(ctx);
        wr.start("a");
        wr.attr("href", model.getHref());
        wr.attr("data-action", model.getAction());
        model.getContent().render(ctx);
        wr.end("a");
    }
}
