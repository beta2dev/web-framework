package ru.beta2.wf.tdd;

import ru.beta2.wf.model.component.Page;
import ru.beta2.wf.model.flow.Dispatch;

/**
 * @author olegn 10.11.2014
 */
public class SimplePage extends Page
{
    public SimplePage(String pathTemplate, String body)
    {
        dispatch(pathTemplate);
        assignRenderer(body);
    }

    public SimplePage(int statusCode, String body)
    {
        dispatch(Dispatch.statusCode(statusCode));
        assignRenderer(body);
    }

    private void assignRenderer(String body)
    {
        renderer((p, ctx) -> ctx.write(body));
    }
}
