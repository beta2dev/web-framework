package ru.beta2.wf.build;

/**
 * @author olegn 14.11.2014
 */
public interface Buildable
{

    void build(BuildContext ctx, BuildResultsCollector collector);

}
