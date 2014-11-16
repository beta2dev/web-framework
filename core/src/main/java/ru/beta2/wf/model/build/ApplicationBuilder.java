package ru.beta2.wf.model.build;

import ru.beta2.wf.model.component.Application;
import ru.beta2.wf.model.component.Component;
import ru.beta2.wf.model.flow.Command;
import ru.beta2.wf.model.flow.FlowLifecycle;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;
import ru.beta2.wf.util.AbstractAttachable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 20:42
 */
public abstract class ApplicationBuilder
{

    public static ApplicationBuilder create(BuildServices services)
    {
        return new DelegateServicesApplicationBuilder(services);
    }

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
        BuildContextImpl ctx = createBuildContext();

        for (BuildStage stage = lifecycle.getNextStage(ctx); stage != null; stage = lifecycle.getNextStage(ctx)) {
            app.accept(stage);
            if (!stage.isPassed()) {
                break;
            }
        }

        return ctx;
    }

    protected abstract BuildContextImpl createBuildContext();

    protected abstract class BuildContextImpl extends AbstractAttachable implements BuildContext, BuildResult
    {

        private final List<String> errors = new ArrayList<>();
        private final List<String> warnings = new ArrayList<>();
        private final List<Command> commands = new ArrayList<>();

        @Override
        public void addWarning(String msg)
        {
            warnings.add(msg);
        }

        @Override
        public List<String> getWarnings()
        {
            return warnings;
        }

        @Override
        public void addError(String msg)
        {
            errors.add(msg);
        }

        @Override
        public List<String> getErrors()
        {
            return errors;
        }

        @Override
        public boolean isPassed()
        {
            return !hasErrors();
        }

        @Override
        public boolean hasErrors()
        {
            return !errors.isEmpty();
        }

        @Override
        public void addCommand(Command cmd)
        {
            commands.add(cmd);
        }

        @Override
        public List<Command> getCommands()
        {
            return commands;
        }
    }
}

class DelegateServicesApplicationBuilder extends ApplicationBuilder
{

    private final BuildServices services;

    DelegateServicesApplicationBuilder(BuildServices services)
    {
        this.services = services;
    }

    @Override
    protected BuildContextImpl createBuildContext()
    {
        return new BuildContextImpl()
        {
            @Override
            public String generateId()
            {
                return services.generateId();
            }

            @Override
            public <M, C extends Component<M>> Renderer<M, C> getRendererForComponent(C component)
            {
                return services.getRendererForComponent(component);
            }

            @Override
            public FlowLifecycle getFlowLifecycle()
            {
                return services.getFlowLifecycle();
            }
        };
    }
}

