package ru.beta2.wf.components;

import ru.beta2.wf.model.Component;
import ru.beta2.wf.model.Supplier;

/**
 * @author olegn 14.11.2014
 */
public class TextContent extends Component<String>
{

    private Supplier<String> text; // todo !!! ??? do escaping ?

    public TextContent text(String text)
    {
        this.text = Supplier.value(text);
        return this;
    }

    // todo !!! ??? what about renderer ? maybe escape there

}
