package ru.beta2.wf.tdd;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import static com.jayway.restassured.RestAssured.with;

/**
 * 09.11.2014
 * olegn
 */
public class AssuredTest
{

    @BeforeClass
    public static void setupAssured()
    {
        RestAssured.baseURI = "http://localhost:8091";
    }

    //
    //  Implementation
    //

    protected ResponseSpecification expect(String expectedBody)
    {
        return with().expect().statusCode(200).body(Matchers.equalTo(expectedBody));
    }

}
