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

    private Renderer<M> renderer;

    private String name;

    private String id;

    public Component()
    {
        Class<? super M> modelClass = getModelClass();
        this.modelKey = modelClass != null ? AttachmentKey.create(modelClass) : null;
    }

    public Renderer<M> getRenderer()
    {
        return renderer;
    }

    public void setRenderer(Renderer<M> renderer)
    {
        this.renderer = renderer;
    }

    public Component<M> renderer(Renderer<M> renderer)
    {
        setRenderer(renderer);
        return this;
    }

    public Component<M> wrap(Renderer<M> renderer)
    {
        setRenderer(new ComponentWrappingRenderer<M>(renderer));
        return this;
    }

    public Component<M> wrap()
    {
        if (renderer == null) {
            throw new NullPointerException("There is no renderer for wrapping");
        }
        return wrap(renderer);
    }

    @Override
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

    @Override
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

    // todo ??? или может быть вообще отказаться от моделей в пользу supplier'ов ?
    // todo ??? как быть с компонентами - а именно с тем, что их надо бы не через строки рендерить, а через

    @Override
    public void render(RenderContext ctx)
    {
        renderer.render(this, ctx);
    }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
    }

    protected Class<? super M> getModelClass()
    {
        return null; // no model by default
    }

    protected M createModel(FlowContext ctx)
    {
        return null; // no model by default
    }

}
