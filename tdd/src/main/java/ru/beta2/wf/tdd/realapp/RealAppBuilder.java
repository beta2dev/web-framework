package ru.beta2.wf.tdd.realapp;

import org.apache.commons.lang3.RandomStringUtils;
import ru.beta2.wf.actions.LinkActionRenderer;
import ru.beta2.wf.actions.NavigatePageAction;
import ru.beta2.wf.components.WebFrameworkJavascript;
import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.component.*;
import ru.beta2.wf.model.render.ComponentWrappingRenderer;
import ru.beta2.wf.model.render.Renderer;
import ru.beta2.wf.model.render.StringRenderer;
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
        Application app = new Application().name("RealApp");
        HtmlTemplateRenderer renderer = new HtmlTemplateRenderer("ru.beta2.wf.tdd.realapp");
        LinkActionRenderer linkActionRenderer = new LinkActionRenderer();
        Renderer stringRenderer = new StringRenderer();

        Layout<Map<String, Object>> layout = new Layout<Map<String, Object>>() {
            @Override
            public Class<? super Map<String, Object>> getModelClass()
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

        Page<?> p2 = new Page<>().dispatch("/second-page").name("second-page").layout(layout);
        p2.addChild(new StaticModelComponent().name("secondPageBlock").renderer(renderer), "dummyBlock");

        Page<?> p1 = new StaticModelPage("/first-page")
            .name("first-page")
            .layout(layout);
        NavigatePageAction nav = new NavigatePageAction();
        nav.page(p2).content("go").id("thisIsFirstPageBlockId").name("firstPageActionAsBlock")
                .renderer(linkActionRenderer)
                .wrap(linkActionRenderer);
        nav.getContent().renderer(stringRenderer); // todo !!! refactore this
        p1.addChild(new NavigatePageAction().page(p2).content("go").id("thisIsFirstPageBlockId").name("firstPageActionAsBlock")
                .wrap(linkActionRenderer), "dummyBlock");

        app.addPages(p1, p2);

        return app;
    }

}
