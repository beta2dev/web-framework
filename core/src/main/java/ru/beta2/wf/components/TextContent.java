package ru.beta2.wf.components;

import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.component.Supplier;
import ru.beta2.wf.model.flow.FlowContext;

/**
 * @author olegn 14.11.2014
 */
public class TextContent extends Component<String>
{

    // todo !!! implement autowire renderers (для текста по умолчанию по идее нужно искейпить, но должна быть и возможность отключать искейп)

    private Supplier<String> text;

    public TextContent text(String text)
    {
        this.text = Supplier.value(text);
        return this;
    }

    // todo !!! ??? what about renderer ? maybe escape there

    @Override
    protected String createModel(FlowContext ctx)
    {
        return text.get(ctx);
    }

    @Override
    protected Class<? super String> getModelClass()
    {
        return String.class;
    }
}
