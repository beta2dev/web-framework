package ru.beta2.wf.tdd;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.component.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author olegn 10.11.2014
 */
public class StaticModelPage extends Page<Map<String,Object>>
{

    private final HashMap<String,Object> model = new HashMap<>();

    public StaticModelPage(String pathTemplate)
    {
        dispatch(pathTemplate);
    }

    public StaticModelPage put(String key, Object value)
    {
        model.put(key, value);
        return this;
    }

    @Override
    public Map<String, Object> getModel(FlowContext ctx)
    {
        return model;
    }

    @Override
    public Class<? super Map<String, Object>> getModelClass()
    {
        return Map.class;
    }
}
