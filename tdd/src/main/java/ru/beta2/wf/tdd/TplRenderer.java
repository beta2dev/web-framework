package ru.beta2.wf.tdd;

import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
import ru.beta2.wf.model.render.OutputBuffer;
import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;

import java.util.Map;

/**
* User: Inc
* Date: 10.11.2014
* Time: 18:22
*/
public class TplRenderer implements Renderer<Map<String, Object>, Renderable<Map<String, Object>>>
{
    private final String template;

    public TplRenderer(String template)
    {
        this.template = template;
    }

    @Override
    public void render(Renderable<Map<String, Object>> renderable, RenderContext ctx)
    {
        Map<String, Object> model = renderable.getModel(ctx);
        System.out.println("RENDER: " + renderable.getRenderName() + ", MODEL: " + model);
        StrSubstitutor str = new StrSubstitutor(model);
        String result = str.replace(getTemplateSource(renderable, template));
//        if (renderable instanceof HasChildren) {
            StrSubstitutor str2 = new StrSubstitutor(new StrLookup<Object>()
            {
                @Override
                public String lookup(String key)
                {
                    Renderable child = renderable.getComponent(key);
                    if (child != null) {
                        OutputBuffer b = ctx.startBuffering();
                        child.render(ctx);
                        return b.stopAndGet();
                    }
                    return "!!! COMPONENT NOT FOUND by key '" + key + "' !!!";
                }
            }, "#{", "}", '#');
            result = str2.replace(result);
//        }
        ctx.write(result);
    }

    protected String getTemplateSource(Renderable component, String template)
    {
        return template;
    }
}
