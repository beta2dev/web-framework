package ru.beta2.wf.components;

import org.apache.commons.io.IOUtils;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.render.RenderContext;

import java.io.IOException;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 20:46
 */
public class WebFrameworkJavascript extends Component<Void>
{

    private final String javascriptCode;

    public WebFrameworkJavascript()
    {
        try {
            // todo !!! use file (or implement minimize and concatenate js in one script)
            javascriptCode = IOUtils.toString(getClass().getResourceAsStream("web-framework.js"), "UTF-8");
        }
        catch (IOException e) {
            throw new RuntimeException(e); // todo !!! handle
        }
    }

    // todo ??? maybe rethink this implementation
    @Override
    public void render(RenderContext ctx)
    {
//        ctx.write("<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-2.1.1.min.js\"></script>\n");
        ctx.write("<script type=\"text/javascript\">\n" + javascriptCode + "\n</script>");
    }

    @Override
    public boolean isRendererRequired()
    {
        return false;
    }
}
