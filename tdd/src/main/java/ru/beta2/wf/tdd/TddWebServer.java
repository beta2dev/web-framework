package ru.beta2.wf.tdd;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.NameVirtualHostHandler;
import ru.beta2.wf.components.TextContent;
import ru.beta2.wf.components.TextContentRenderer;
import ru.beta2.wf.flow.ApplicationHttpHandler;
import ru.beta2.wf.model.build.ApplicationBuilder;
import ru.beta2.wf.model.build.BuildResult;
import ru.beta2.wf.model.build.DefaultApplicationBuilder;
import ru.beta2.wf.model.component.Application;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.component.CompositeComponent;
import ru.beta2.wf.model.component.Page;
import ru.beta2.wf.model.flow.FlowController;
import ru.beta2.wf.tdd.realapp.RealAppBuilder;

/**
 * 09.11.2014
 * olegn
 */
public class TddWebServer
{

    public static void main(String[] args)
    {
        new TddWebServer().run();
    }

    private final FlowController flowController;
    private final DefaultApplicationBuilder appBuilder;

    public TddWebServer()
    {
        this.flowController = new FlowController();
        this.appBuilder = new DefaultApplicationBuilder();
        appBuilder.registerRendererForComponent(TextContent.class, new TextContentRenderer());
    }

    private void run()
    {
        Undertow server = Undertow.builder()
                .addHttpListener(8091, "localhost")
                .setHandler(buildRootHandler())
                .build();
        server.start();
    }

    private HttpHandler buildRootHandler()
    {
        NameVirtualHostHandler virtualHostHandler = new NameVirtualHostHandler();

        // default app
        virtualHostHandler.setDefaultHandler(createHttpHandler(buildSimpleApp()));

        // custom-error-pages
        virtualHostHandler.addHost("custom-error-pages", createHttpHandler(buildCustomErrorPagesApp()));

        // real-app
        virtualHostHandler.addHost("real-app", createHttpHandler(buildRealApp()));

        return virtualHostHandler;
    }

    private HttpHandler createHttpHandler(Application app)
    {
        System.out.println("Build app: " + app);
        BuildResult res = appBuilder.build(app);
        System.out.println("WARNINGS: " + res.getWarnings());
        System.out.println("ERRORS: " + res.getErrors());
        if (!res.isPassed()) {
            throw new RuntimeException("Build is not passed");
        }

        return new ApplicationHttpHandler(flowController, res.getCommands());
    }

    private Application buildSimpleApp()
    {
        Application app = new Application();
        app.name("SimpleApp");
        app.addPages(
                new SimplePage("/", "This is index page"),
                new SimplePage("/aaa/bbb", "Bbb page"),
                new SimplePage("/aaa", "Aaa page"),
                new SimplePage("/hello-world", "Hello, world!"),
                new TemplatePage("/page-template", "AAA: ${aaa}, BBB: ${bbb}")
        );

        Page pageWithBlock = new Page<>()
                .dispatch("/page-with-block")
                .renderer(new ComponentsRenderer("page"))
                .child(new Component<>().renderer(new ComponentsRenderer("block")))
                ;
        app.addPage(pageWithBlock);

        Page pageWithInnerBlocks = new Page().dispatch("/page-with-inner-blocks").renderer(new ComponentsRenderer("page"));
        CompositeComponent blockA = new CompositeComponent().renderer(new ComponentsRenderer("block-a"));
        CompositeComponent blockB = new CompositeComponent().renderer(new ComponentsRenderer("block-b"));
        CompositeComponent blockC = new CompositeComponent().renderer(new ComponentsRenderer("block-c"));
        pageWithInnerBlocks.addChildren(blockA, blockB);
        blockA.addChild(blockC);
        app.addPage(pageWithInnerBlocks);

        Page pageNamedComponents = new Page().dispatch("/named-components").renderer(new TplRenderer("[BBB:#{bbb}] [AAA:#{aaa}]"));
        pageNamedComponents.addChildren(
                new Component<>().renderer(new ComponentsRenderer("block-a")).name("aaa"),
                new Component<>().renderer(new ComponentsRenderer("block-b")).name("bbb")
        );
        app.addPage(pageNamedComponents);

        Page secondPage = new SimplePage("/second-page", "This is second page");
        Page pageToPageNav = new StaticModelPage("/page-to-page-nav")
                .put("second-page-link", secondPage.getLink())
                .renderer(new TplRenderer("<page><second-page-link>${second-page-link}</second-page-link></page>"));
        app.addPages(pageToPageNav, secondPage);

        return app;
    }

    private Application buildCustomErrorPagesApp()
    {
        Application app = new Application();
        app.name("CustomErrorPages");
        app.addPage(new SimplePage(404, "404 PAGE NOT FOUND"));
        return app;
    }

    private Application buildRealApp()
    {
        return new RealAppBuilder().create();
    }

}


