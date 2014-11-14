package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.FlowContext;

/**
 * @author olegn 14.11.2014
 */
public interface Supplier<T>
{

    T get(FlowContext ctx);

    static <T> Supplier<T> value(T value)
    {
        return new Supplier<T>()
        {
            @Override
            public T get(FlowContext ctx)
            {
                return value;
            }
        };
    }

}
