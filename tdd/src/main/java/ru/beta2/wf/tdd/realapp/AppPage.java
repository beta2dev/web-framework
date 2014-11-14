package ru.beta2.wf.tdd.realapp;

import ru.beta2.wf.flow.FlowContext;
import ru.beta2.wf.model.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * todo !!! ??? remove this dublicate
 * User: Inc
 * Date: 12.11.2014
 * Time: 15:20
 */
public class AppPage extends Page<Map<String, Object>>
{
    public AppPage(String pathTemplate)
    {
        super(pathTemplate);
    }

    @Override
    protected Class<? super Map<String, Object>> getModelClass()
    {
        return Map.class;
    }

    @Override
    protected Map<String, Object> createModel(FlowContext ctx)
    {
        Map<String, Object> model = new HashMap<>();
        populateModel(model);
        return model;
    }

    protected void populateModel(Map<String, Object> model)
    {
        // do nothing here
    }
}
