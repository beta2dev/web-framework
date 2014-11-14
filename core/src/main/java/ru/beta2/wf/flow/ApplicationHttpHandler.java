package ru.beta2.wf.flow;

import io.undertow.server.DefaultResponseListener;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathTemplateHandler;
import ru.beta2.wf.model.Application;
import ru.beta2.wf.model.Page;
import ru.beta2.wf.util.AbstractAttachable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author olegn 09.11.2014
 */
public class ApplicationHttpHandler implements HttpHandler
{

    private final StatusCodeListener statusCodeListener = new StatusCodeListener();

    private final PathTemplateHandler pathTemplateHandler = new PathTemplateHandler();

    // todo DEFFERED handle if pathTemplate and statusCode both are null
    // todo DEFFERED handle if pathTemplate and statusCode both are set

    public ApplicationHttpHandler(Application app)
    {
        for (Page<?> p : app.getPages()) {
            if (p.getPathTemplate().isPresent()) {
                pathTemplateHandler.add(p.getPathTemplate().get(), (e)->p.render(new FlowContextImpl(e)));
            }
            if (p.getStatusCode().isPresent()) {
                statusCodeListener.addRequestHandler(p.getStatusCode().get(), (e)->p.render(new FlowContextImpl(e)));
            }
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

class FlowContextImpl extends AbstractAttachable implements FlowContext
{

    // todo !!! implement nesting output buffers

    private final HttpServerExchange exchange;

    private OutputBufferImpl buffer;

    FlowContextImpl(HttpServerExchange exchange)
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

