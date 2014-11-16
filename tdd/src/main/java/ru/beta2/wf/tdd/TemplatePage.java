package ru.beta2.wf.tdd;

import ru.beta2.wf.model.flow.Dispatch;
import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.component.Page;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * todo !!! rename this class
 * User: Inc
 * Date: 10.11.2014
 * Time: 17:26
 */
class TemplatePage extends Page<Map<String,Object>>
{

    public TemplatePage(String pathTemplate, String template)
    {
        dispatch(pathTemplate);
        renderer(new TplRenderer(template));
    }

    public TemplatePage(int statusCode, String template)
    {
        dispatch(Dispatch.statusCode(statusCode));
        renderer(new TplRenderer(template));
    }

    @Override
    protected Map<String,Object> createModel(FlowContext ctx)
    {
        HashMap<String,Object> model = new HashMap<>();
        for (Map.Entry<String,Deque<String>> e : ctx.getQueryParameters().entrySet()) {
            model.put(e.getKey(), "Q-" + e.getValue().getFirst());
        }
        System.out.println("MODEL: " + model);
        return model;
    }

    @Override
    public Class<? super Map<String, Object>> getModelClass()
    {
        return Map.class;
    }

}
