package ru.beta2.wf.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 09.11.2014
 * olegn
 */
public class Application
{

    private final Collection<Page<?>> pages = new ArrayList<>();

    // todo !!! maybe implement
//    private Renderer defaultRenderer;

    public void addPage(Page<?> page)
    {
        pages.add(page);
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

}
