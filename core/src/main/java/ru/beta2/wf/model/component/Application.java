package ru.beta2.wf.model.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * 09.11.2014
 * olegn
 */
public class Application implements Visitable
{

    private String name;

    private final Collection<Page<?>> pages = new ArrayList<>();

    private Collection<Layout<?>> layouts;

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
        if (page.getLayout() != null) {
            addLayout(page.getLayout());
        }
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

        for (Layout l : getLayouts()) {
            l.accept(visitor);
        }

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

    //
    //  Implementation details
    //

    private void addLayout(Layout<?> layout)
    {
        if (layouts == null) {
            layouts = new HashSet<>();
        }
        layouts.add(layout);
    }

    private Collection<Layout<?>> getLayouts()
    {
        if (layouts == null) {
            return Collections.emptySet();
        }
        return layouts;
    }
}
