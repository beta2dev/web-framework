package ru.beta2.wf.model.flow;

import java.util.Arrays;

/**
 * @author olegn 15.11.2014
 */
public class Dispatch
{

    private Dispatch() {}

    public static Dispatch pathTemplate(String pathTemplate)
    {
        return new PathTemplate(pathTemplate);
    }

    public static Dispatch statusCode(int statusCode)
    {
        return new StatusCode(statusCode);
    }

    public static Dispatch composite(Dispatch... dispatches)
    {
        return new Composite(Arrays.asList(dispatches));
    }

    public boolean is(Class<?> dispatchClass)
    {
        return dispatchClass.isInstance(this);
    }

    public <T> T as(Class<T> dispatchClass)
    {
        return dispatchClass.cast(this);
    }

    public static class PathTemplate extends Dispatch
    {
        private final String value;

        private PathTemplate(String value)
        {
            this.value = value;
        }

        public String get()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return "PathTemplate{" + value + '}';
        }
    }

    public static class StatusCode extends Dispatch
    {
        private final int value;

        private StatusCode(int value)
        {
            this.value = value;
        }

        public int get()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return "StatusCode{" + value + '}';
        }
    }

    public static class Composite extends Dispatch
    {
        private final Iterable<Dispatch> value;

        public Composite(Iterable<Dispatch> value)
        {
            this.value = value;
        }

        public Iterable<Dispatch> get()
        {
            return value;
        }
    }

}
