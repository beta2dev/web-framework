package ru.beta2.wf.model.build;

import ru.beta2.wf.model.component.Application;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.flow.Command;
import ru.beta2.wf.model.render.Renderer;
import ru.beta2.wf.util.AbstractAttachable;

import java.util.List;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 20:42
 */
public class ApplicationBuilder
{

    private final BuildServices services;

    public ApplicationBuilder(BuildServices services)
    {
        this.services = services;
    }

//
    //  Default build stages:
    //  1) build
    //  2) generate id
    //  3) assign renderer
    //  4) verify


    // ----
    //  0) call Buildable.build()
    //  1) assign component id
    //  2) assign renderer (and check if there is no renderer)
    //  3) gather pages and actions (for connection to flow)

    public BuildResult build(Application app)
    {
        return build(app, new DefaultBuildLifecycle());
    }

    public BuildResult build(Application app, BuildLifecycle lifecycle)
    {
        BuildContextImpl ctx = new BuildContextImpl();

        for (BuildStage stage = lifecycle.getNextStage(ctx); stage != null; stage = lifecycle.getNextStage(ctx)) {
            app.accept(stage);
        }

        return null;
    }

    private class BuildContextImpl extends AbstractAttachable implements BuildContext, BuildResult
    {

        // todo !!! implement
//        private List<Command>

        @Override
        public String generateId()
        {
            return services.generateId();
        }

        @Override
        public <M> Renderer<M> getRendererForComponent(Component<M> component)
        {
            return services.getRendererForComponent(component);
        }

        @Override
        public void registerCommand(Command cmd)
        {
            // todo implement - короче с командами нужно будет поподробнее разобраться - но суть такая, что добавляем в команду pathTemplate (или может назвать более обще pathString/еще как-то - или ладно пусть будет pathTemplate), по которой она вызывается
        }

        @Override
        public List<Command> getCommands()
        {
            return null;// todo implement
        }
    }

}

