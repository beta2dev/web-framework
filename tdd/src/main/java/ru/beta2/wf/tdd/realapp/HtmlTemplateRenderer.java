package ru.beta2.wf.tdd.realapp;

import org.apache.commons.io.IOUtils;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.tdd.TplRenderer;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 14:25
 */
public class HtmlTemplateRenderer extends TplRenderer
{

    public HtmlTemplateRenderer(String templateRoot)
    {
        super(templateRoot);
    }

    @Override
    protected String getTemplateSource(Renderable component, String template)
    {
        template = "/" + template + "/" + component.getRenderName() + ".html";
        System.out.println("HTML-TPL: " + template);
        try {
            InputStream stream = getClass().getResourceAsStream(template);
            if (stream == null) {
                throw new RuntimeException("Template resource '" + template + "' not found (renderable '" + component.getRenderName() + "')");
            }
            return IOUtils.toString(stream, "UTF-8");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
