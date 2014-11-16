package ru.beta2.wf.model.component;

/**
 * User: Inc
 * Date: 13.11.2014
 * Time: 22:34
 */
public abstract class Action<M extends ActionModel> extends Component<M>
{

    // todo ??? what about CompositeComponent actions (maybe form) - use content for it ?

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
    }

    public abstract String getActionHandler();

}
