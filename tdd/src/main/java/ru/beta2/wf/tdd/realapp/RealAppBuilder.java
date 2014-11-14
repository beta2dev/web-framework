package ru.beta2.wf.tdd.realapp;

import org.apache.commons.lang3.RandomStringUtils;
import ru.beta2.wf.actions.NavigatePageAction;
import ru.beta2.wf.components.WebFrameworkJavascript;
import ru.beta2.wf.flow.FlowContext;
import ru.beta2.wf.model.*;
import ru.beta2.wf.render.ComponentWrappingRenderer;
import ru.beta2.wf.tdd.StaticModelComponent;
import ru.beta2.wf.tdd.StaticModelPage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author olegn 12.11.2014
 */
public class RealAppBuilder
{

    public Application create()
    {
        Application app = new Application();
        HtmlTemplateRenderer renderer = new HtmlTemplateRenderer("ru.beta2.wf.tdd.realapp");
        Renderer wrappingRenderer = new ComponentWrappingRenderer(renderer);

        Layout<Map<String, Object>> layout = new Layout<Map<String, Object>>() {
            @Override
            protected Class<? super Map<String, Object>> getModelClass()
            {
                return Map.class;
            }

            @Override
            protected Map<String, Object> createModel(FlowContext ctx)
            {
                HashMap<String, Object> model = new HashMap<>();
                model.put("staticRandomContent", "WasGenerated-" + RandomStringUtils.randomAlphanumeric(30));
                return model;
            }
        }
                .name("dummy-layout")
                .renderer(renderer);

        layout.addChild(new WebFrameworkJavascript().name("frameworkJavaScript"));

        Page<?> p2 = new Page<>("/second-page").name("second-page").layout(layout);
        p2.addChild(new Component<>().name("secondPageBlock").renderer(renderer), "dummyBlock");

        Page<?> p1 = new StaticModelPage("/first-page")
            .name("first-page")
            .layout(layout);
//        p1.addChild(new StaticModelComponent()
//                .put("secondPageLink", p2.getLink())
//                .id("thisIsFirstPageBlockId").name("firstPageBlock")
//                .renderer(wrappingRenderer), "dummyBlock");
        // todo !!! use here
        p1.addChild(new NavigatePageAction().page(p2).id("thisIsFirstPageBlockId").name("firstPageActionAsBlock").wrap(renderer));

        app.addPages(p1, p2);

        return app;
    }

}
