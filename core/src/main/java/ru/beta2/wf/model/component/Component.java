package ru.beta2.wf.model.component;

import ru.beta2.wf.model.flow.FlowContext;
import ru.beta2.wf.model.render.ComponentWrappingRenderer;
import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.model.render.Renderable;
import ru.beta2.wf.model.render.Renderer;
import ru.beta2.wf.util.AbstractAttachable;
import ru.beta2.wf.util.AttachmentKey;

/**
 * todo !!! ??? наверное следует переименовать пакет model в component ?
 *
 *      или засунуть внутрь model: build, flow(? этот может нет или без ApplicationHttpHandler), component (бывший model), render (и в render перенести Renderable и Renderer)
 *
 * Lifecycle - вообще у нас два lifecycl'a получается: 1) build-lifecycle 2) flow-lifecycle
 *
 * И если build-lifecycle можно сделать статическим (то есть жестко его зашить),
 * то вот flow-lifecycle хорошо бы сделать конфигурируемым (в рамках приложения).
 *      То есть нам надо задать это параметризованным типом в Application и потом спускать этот тип через страницы во все компоненты
 *
 * Сделать визиторов. И, например, определить интерфейс Buildable.build(BuildContext, BuildResultsCollector (собирает ошибки и успехи) ) и визитора BuildVisitor, который проходится и собирает результаты сборки компонентов
 *      вообще Buildable нужно будет сделать на Component'e (для генерации отсутствующих id, например)
 *
 * User: Inc
 * Date: 10.11.2014
 * Time: 17:08
 */
public class Component<M> extends AbstractAttachable implements Renderable<M>, Visitable
{

    private final AttachmentKey<M> modelKey;

    private Class<M> modelClass;
    private Supplier<M> modelSupplier;

    private Renderer<M, ? extends Renderable<M>> renderer;

    private String name;

    private String id;

    public Component()
    {
        Class<? super M> modelClass = getModelClass();
        this.modelKey = modelClass != null ? AttachmentKey.create(modelClass) : null;
    }

    public Renderer<M, ? extends Renderable<M>> getRenderer()
    {
        return renderer;
    }

    public void setRenderer(Renderer<M, ? extends Renderable<M>> renderer)
    {
        this.renderer = renderer;
    }

    public Component<M> renderer(Renderer<M, ? extends Renderable<M>> renderer)
    {
        setRenderer(renderer);
        return this;
    }

    public boolean isRendererRequired()
    {
        return renderer == null;
    }

    public Component<M> wrap(Renderer<M, ? extends Renderable<M>> renderer)
    {
        setRenderer(new ComponentWrappingRenderer<>(renderer));
        return this;
    }

    public Component<M> wrap()
    {
        if (renderer == null) {
            throw new NullPointerException("There is no renderer for wrapping");
        }
        return wrap(renderer);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Component<M> name(String name)
    {
        setName(name);
        return this;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Component<M> id(String id)
    {
        setId(id);
        return this;
    }

    @Override
    public String getRenderId()
    {
        return getId();
    }

    @Override
    public String getRenderName()
    {
        return getName();
    }

    @Override
    public M getModel(FlowContext ctx)
    {
        if (modelKey == null) {
            return null;
        }
        M model =  ctx.getAttachment(modelKey);
        if (model == null) {
            model = createModel(ctx);
            ctx.putAttachment(modelKey, model);
        }
        return model;
    }

    @Override
    public Renderable<?> getComponent(String name)
    {
        return null; // no subcomponents in component
    }

    public Component<M> model(Class<M> modelClass, Supplier<M> modelSupplier)
    {
        this.modelClass = modelClass;
        this.modelSupplier = modelSupplier;
        return this;
    }

    @Override
    public void render(RenderContext ctx)
    {
        render(renderer, ctx);
    }

    protected void render(Renderer<?, ? extends Renderable<?>> renderer, RenderContext ctx)
    {
        // dirty hack
        ((Renderer<M, Component<M>>)renderer).render(this, ctx);
    }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
    }

    public Class<? super M> getModelClass()
    {
        return modelClass; // no model by default (null)
    }

    protected M createModel(FlowContext ctx)
    {
        return modelSupplier != null
                ? modelSupplier.get(ctx)
                : null; // no model by default
    }

    @Override
    public String toString()
    {
        return getClass().getName() + "{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
