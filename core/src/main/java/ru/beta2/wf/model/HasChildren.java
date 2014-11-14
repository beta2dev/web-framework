package ru.beta2.wf.model;

import java.util.List;

/**
 * User: Inc
 * Date: 12.11.2014
 * Time: 23:27
 */
public interface HasChildren
{

//    List<? extends Renderable<?>> getChildren();

    Renderable<?> getChildByName(String name);

}
