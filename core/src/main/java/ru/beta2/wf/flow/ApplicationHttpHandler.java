package ru.beta2.wf.flow;

import io.undertow.server.DefaultResponseListener;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathTemplateHandler;
import ru.beta2.wf.model.flow.Command;
import ru.beta2.wf.model.flow.Dispatch;
import ru.beta2.wf.model.flow.FlowController;
import ru.beta2.wf.model.render.OutputBuffer;
import ru.beta2.wf.model.render.RenderContext;
import ru.beta2.wf.util.AbstractAttachable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo !!! refactore this into separate module (undertow-specific)
 * @author olegn 09.11.2014
 */
public class ApplicationHttpHandler implements HttpHandler
{

    private final StatusCodeListener statusCodeListener = new StatusCodeListener();

    private final PathTemplateHandler pathTemplateHandler = new PathTemplateHandler();

    // todo DEFFERED handle if pathTemplate and statusCode both are null
    // todo DEFFERED handle if pathTemplate and statusCode both are set

    private final FlowController ctl;

    public ApplicationHttpHandler(FlowController ctl, List<Command> commands)
    {
        this.ctl = ctl;

        for (Command cmd : commands) {
            System.out.println("COMMAND: " + cmd.getDispatch());
            registerDispatch(cmd, cmd.getDispatch());
        }
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception
    {
        exchange.addDefaultResponseListener(statusCodeListener);
        exchange.startBlocking();
        pathTemplateHandler.handleRequest(exchange);
        exchange.endExchange();
    }

    // todo DEFFERED TEST composite dispatch
    private void registerDispatch(Command cmd, Dispatch d)
    {
        if (d.is(Dispatch.PathTemplate.class)) {
            pathTemplateHandler.add(d.as(Dispatch.PathTemplate.class).get(), (e)->ctl.run(cmd, new RenderContextImpl(e)));
        }
        if (d.is(Dispatch.StatusCode.class)) {
            statusCodeListener.addRequestHandler(d.as(Dispatch.StatusCode.class).get(), (e)->ctl.run(cmd, new RenderContextImpl(e)));
        }
        if (d.is(Dispatch.Composite.class)) {
            for (Dispatch d0 : d.as(Dispatch.Composite.class).get()) {
                registerDispatch(cmd, d0);
            }
        }
    }
}

class StatusCodeListener implements DefaultResponseListener
{

    private final Map<Integer, HttpHandler> handlers = new HashMap<>();

    public void addRequestHandler(int statusCode, HttpHandler handler)
    {
        handlers.put(statusCode, handler);
    }

    @Override
    public boolean handleDefaultResponse(HttpServerExchange exchange)
    {
        HttpHandler handler = handlers.get(exchange.getResponseCode());
        if (handler != null) {
            try {
                handler.handleRequest(exchange);
            }
            catch (Exception e) {
                throw new RuntimeException(e); // todo !!! handle errors in error pages
            }
            return true;
        }
        return false;
    }
}

class RenderContextImpl extends AbstractAttachable implements RenderContext
{

    // todo !!! implement nesting output buffers

    private final HttpServerExchange exchange;

    private OutputBufferImpl buffer;

    RenderContextImpl(HttpServerExchange exchange)
    {
        this.exchange = exchange;
    }

    @Override
    public Map<String, Deque<String>> getQueryParameters()
    {
        return exchange.getQueryParameters();
    }

    @Override
    public void write(String s)
    {
        try {
            if (buffer == null) {
                exchange.getOutputStream().write(s.getBytes(Charset.forName("UTF-8"))); // todo !!! use charset from somewhere
            }
            else {
                buffer.b.append(s);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e); // todo !!! handle
        }
    }

    @Override
    public OutputBuffer startBuffering()
    {
        if (buffer != null) {
            throw new IllegalStateException("Nested output buffering is not supported for now");
        }
        buffer = new OutputBufferImpl();
        return buffer;
    }

    private class OutputBufferImpl implements OutputBuffer
    {

        private final StringBuilder b = new StringBuilder();

        @Override
        public String stopAndGet()
        {
            buffer = null;
            return b.toString();
        }
    }

}

