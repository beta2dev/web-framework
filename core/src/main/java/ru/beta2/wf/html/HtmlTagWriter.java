package ru.beta2.wf.html;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 18:46
 */
public interface HtmlTagWriter
{

    void start(String tag);

    void attr(String name, String value);

    void end(String tag);

}
