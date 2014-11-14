package ru.beta2.wf.tdd;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;

/**
 * User: Inc
 * Date: 10.11.2014
 * Time: 20:40
 */
public class NavigationTest extends AssuredTest
{

    @Test
    public void testPageToPageNav()
    {
        // 1) загрузить страницу, содержащую ссылку на другую страницу
        // 2) извлечь ссылку со страницы
        String link = get("/page-to-page-nav").then().extract().xmlPath().getString("page.second-page-link");
//        System.out.println("LINK: " + link);
        // 3) перейти по ссылке и проверить, что получили новую страницу
        expect("This is second page").get(link);
    }

}
