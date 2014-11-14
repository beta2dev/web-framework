package ru.beta2.wf.tdd;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author olegn 12.11.2014
 */
public class RealAppTest
{

//    private WebClient webClient;
    private WebTester wt;

    @Before
    public void setup()
    {
        wt = new WebTester();
        wt.setDialog(new HtmlUnitTestingEngineImpl() {
            @Override
            protected WebClient createWebClient()
            {
                WebClient webClient = super.createWebClient();
                webClient.setAjaxController(new NicelyResynchronizingAjaxController());
                webClient.addRequestHeader("Host", "real-app");
                return webClient;
            }
        });

        wt.setBaseUrl("http://localhost:8091");
    }

    @Test
    public void testAjaxNavigateBetweenPagesWithCommonLayout() throws IOException
    {
        wt.beginAt("/first-page");
        String r1 = wt.getElementById("static-random-content").getTextContent();

        System.out.println("1st PAGE: " + wt.getPageSource());

        wt.clickLink("go-second-page");

        System.out.println("2nd PAGE: " + wt.getPageSource());

        wt.assertTextInElement("second-page-content", "This is second page");
        wt.assertTextInElement("static-random-content", r1);
    }

    // todo !!! test one layout inherit another layout (LayoutA -> LayoutB -> LayoutC: navigate pages same level, navigate pages level up, navigate pages level down)

//    @Test
//    public void testAjaxNavigateBetweenPagesWithCommonLayout() throws IOException
//    {
//        HtmlPage p1 = getPage("/first-page");
//        String r1 = p1.getElementById("static-content").getAttribute("r");
//
//        HtmlPage p2 = p1.getAnchorByName("go-second-page").click();
//
//        Assert.assertSame("Second page is different object", p1, p2);
//        Assert.assertEquals("Wrong second page content", "This is second page", p2.getElementById("second-page-content").get);
//        Assert.assertEquals("Static content changed", r1, p2.getElementById("static-content").getAttribute("r"));
//    }
//
//    //
//    //  Implementation details
//    //
//
//    private HtmlPage getPage(String url)
//    {
//        try {
//            webClient.getPage("http://localhost:8091" + url);
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
