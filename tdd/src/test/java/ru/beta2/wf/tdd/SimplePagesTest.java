package ru.beta2.wf.tdd;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;

/**
 * Некоторые базовые тесты страниц.
 * @author olegn 09.11.2014
 */
public class SimplePagesTest extends AssuredTest
{

    @Test
    public void testIndex()
    {
        expect("This is index page").get("/");
    }

    @Test
    public void testInnerPages()
    {
        expect("Aaa page").get("/aaa");
        expect("Bbb page").get("/aaa/bbb");
        expect("Hello, world!").get("/hello-world");
    }

    @Test
    public void testDefault404NotFound()
    {
        with().expect().statusCode(404).body(isEmptyString())
                .get("/unknown/page");
    }

    @Test
    public void testCustom404NotFound()
    {
        with().header("Host", "custom-error-pages")
                .expect().statusCode(404).body(equalTo("404 PAGE NOT FOUND"))
                .get("/unknown/page");
    }

    @Test
    public void testPageTemplate()
    {
        expect("AAA: Q-123, BBB: Q-456").get("/page-template?aaa=123&bbb=456");
    }

}
