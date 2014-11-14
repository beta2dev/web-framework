package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.Command;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 22:34
 */
public abstract class Action<M extends ActionModel> extends Component<M> implements Command
{

    // todo ??? what about href / pathTemplates ?

    // todo !!! implement

    // todo ??? what about CompositeComponent actions (maybe form) - use content for it ?


    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
    }
}
