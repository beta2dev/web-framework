package ru.beta2.wf.tdd;

import org.junit.Test;

/**
 * Тестирование компонентной модели.
 * User: Inc
 * Date: 10.11.2014
 * Time: 16:59
 */
public class ComponentsTest extends AssuredTest
{

    @Test
    public void testPageWithBlock()
    {
        expect("<page><block></block></page>").get("/page-with-block");
    }

    @Test
    public void testPageWithInnerBlocks()
    {
        expect("<page><block-a><block-c></block-c></block-a><block-b></block-b></page>").get("/page-with-inner-blocks");
    }

    @Test
    public void testNamedComponents()
    {
        expect("[BBB:<block-b></block-b>] [AAA:<block-a></block-a>]").get("/named-components");
    }

//    @Test
    public void testRenderSomeComponents()
    {
        // todo ??? implement with command по всей видимости
        expect("BLOCK-A&\\/&BLOCK-B&\\/&BLOCK-C").post("/render-some-components");
    }

}
