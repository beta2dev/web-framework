package ru.beta2.wf.tdd;

import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
import ru.beta2.wf.flow.FlowContext;
import ru.beta2.wf.flow.OutputBuffer;
import ru.beta2.wf.model.*;

import java.util.Map;

/**
* User: Inc
* Date: 10.11.2014
* Time: 18:22
*/
public class TplRenderer implements Renderer
{
    private final String template;

    public TplRenderer(String template)
    {
        this.template = template;
    }

    @Override
    public void render(Renderable component, FlowContext ctx)
    {
        StrSubstitutor str = new StrSubstitutor((Map<String, Object>) component.getModel(ctx));
        String result = str.replace(getTemplateSource(component, template));
        if (component instanceof HasChildren) {
            StrSubstitutor str2 = new StrSubstitutor(new StrLookup<Object>()
            {
                @Override
                public String lookup(String key)
                {
                    Renderable child = ((HasChildren) component).getChildByName(key);
                    if (child != null) {
                        OutputBuffer b = ctx.startBuffering();
                        child.render(ctx);
                        return b.stopAndGet();
                    }
                    return "!!! COMPONENT NOT FOUND by key '" + key + "'";
                }
            }, "#{", "}", '#');
            result = str2.replace(result);
        }
        ctx.write(result);
    }

    protected String getTemplateSource(Renderable component, String template)
    {
        return template;
    }
}
