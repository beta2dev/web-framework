package ru.beta2.wf.model.component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 09.11.2014
 * olegn
 */
public class Application implements Visitable
{

    private String name;

    private final Collection<Page<?>> pages = new ArrayList<>();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Application name(String name)
    {
        this.name = name;
        return this;
    }

    public void addPage(Page<?> page)
    {
        pages.add(page);
    }

    public Application page(Page<?> page)
    {
        addPage(page);
        return this;
    }

    public void addPages(Page<?>... pages)
    {
        for (Page<?> p : pages) {
            addPage(p);
        }
    }

    public Collection<Page<?>> getPages()
    {
        return pages;
    }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);

        for (Page p : pages) {
            p.accept(visitor);
        }
    }

    @Override
    public String toString()
    {
        return "Application{" +
                "name='" + name + '\'' +
                '}';
    }
}
