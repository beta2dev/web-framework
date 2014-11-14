package ru.beta2.wf.html;

import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderer;
import ru.beta2.wf.util.AttachmentKey;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 18:28
 */
public abstract class AbstractHtmlTagRenderer<M> implements Renderer<M>
{

    private static final AttachmentKey<HtmlTagWriter> HTML_TAG_WRITER_ATTACHMENT_KEY = AttachmentKey.create(HtmlTagWriter.class);

    protected HtmlTagWriter getHtmlTagWriter(RenderContext ctx)
    {
        HtmlTagWriter wr = ctx.getAttachment(HTML_TAG_WRITER_ATTACHMENT_KEY);
        if (wr == null) {
            wr = new HtmlTagWriterImpl(ctx);
            ctx.putAttachment(HTML_TAG_WRITER_ATTACHMENT_KEY, wr);
        }
        return wr;
    }

}

class HtmlTagWriterImpl implements HtmlTagWriter
{

    private final RenderContext ctx;

    HtmlTagWriterImpl(RenderContext ctx)
    {
        this.ctx = ctx;
    }

    @Override
    public void start(String tag)
    {
        // todo implement
    }

    @Override
    public void attr(String name, String value)
    {
        // todo implement
    }

    @Override
    public void end(String tag)
    {
        // todo implement
    }
}
