package ru.beta2.wf.tdd;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.component.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 21:32
 */
public class StaticModelComponent extends Component<Map<String, Object>>
{

    private final HashMap<String,Object> model = new HashMap<>();

    public StaticModelComponent put(String key, Object value)
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
    protected Class<? super Map<String, Object>> getModelClass()
    {
        return Map.class;
    }

}
