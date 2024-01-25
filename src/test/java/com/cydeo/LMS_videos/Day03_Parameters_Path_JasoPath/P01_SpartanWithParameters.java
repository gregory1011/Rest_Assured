package com.cydeo.LMS_videos.Day03_Parameters_Path_JasoPath;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class P01_SpartanWithParameters extends SpartanTestBase {


    /*
    * Given accept type isJason
    * And id parameter value is 24
    * When user sends GET request to /api/spartans/{id}
    *The response status code should be 200
    *And response content-type: application/json
    *And "Julio" should be in response payload(body)
    */

    @DisplayName("GET Spartan ")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 24)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.getContentType());

        assertTrue(response.body().asString().contains("Julio"));


    }


   /*
    * Given accept type is Json
    * And id param value is 500
    * When user sends GET request to /api/spartans{id}
    * The response status code should be 404
    * And response content-type: application/json
    * And "Not Found" message should be in response payload
    */

    @DisplayName("GET - Spartan Content type")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 500)
                .when().get("/api/spartans{id}");

        response.prettyPrint();


       // assertEquals(404, response.getStatusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        assertEquals("application/json", response.getContentType());
        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
        Given Accept type is Json
        And query parameter values are:
            gender|Female
            nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */



    @DisplayName("GET Request to /api/spartans/search with Query Params")
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        response.prettyPrint();

//        Then response status code should be 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

//        And response content-type: application/json
        assertEquals("application/json", response.contentType());

//        And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

//        And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

    }



    @DisplayName("GET Request to /api/spartans/search with Query Params")
    @Test
    public void test4(){

        Map<String , Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        response.prettyPrint();

//        Then response status code should be 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

//        And response content-type: application/json
        assertEquals("application/json", response.contentType());

//        And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

//        And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

    }


}
