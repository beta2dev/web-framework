package ru.beta2.wf.model.component;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 21:21
 */
public class ComponentVisitorTest
{

    @Test
    public void testAcceptVisitorWithComponents()
    {
        Application app = new Application()
                .page(
                        new Page<>().name("PAGE-1")
                            .child(new Component<>().name("COMPONENT-111"))
                            .child(new Component<>().name("COMPONENT-222"))
                )
                .page(
                        new Page<>().name("PAGE-2")
                            .child(new CompositeComponent<>().name("COMPONENT-333")
                                    .child(new DummyAction().name("ACTION-444"))
                                    .child(new Component<>().name("COMPONENT-555"))
                            )
                )
                ;

        DummyVisitor v = new DummyVisitor();
        app.accept(v);
        Assert.assertEquals("Wrong visits", "APP, page:PAGE-1, comp:COMPONENT-111, comp:COMPONENT-222, page:PAGE-2, comp:COMPONENT-333, action:ACTION-444, comp:COMPONENT-555, ", v.toString());

    }

    public static class DummyAction extends Action {
        @Override
        public String getActionHandler()
        {
            return "dummy";
        }
    }

    public static class DummyVisitor implements ComponentVisitor
    {

        StringBuilder b = new StringBuilder();

        @Override
        public void visit(Application app)
        {
            b.append("APP, ");
        }

        @Override
        public void visit(Component<?> component)
        {
            tag("comp", component);
        }

        @Override
        public void visit(Page<?> page)
        {
            tag("page", page);
        }

        @Override
        public void visit(Action<?> action)
        {
            tag("action", action);
        }

        @Override
        public String toString()
        {
            return b.toString();
        }

        void tag(String tag, Component<?> component)
        {
            b.append(tag).append(":").append(component.getName()).append(", ");
        }
    }

}
