package com.cydeo.LMS_videos.Day02_Headers_Parameters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class negative_Spartan_Tests {

    @BeforeAll
    public static void init() {
       // String url = "http://3.85.47.176:8000";
        RestAssured.baseURI = "http://3.85.47.176:8000";
    }

   /*
    * Given accept content type is application/json
    * When user sends GET request /api/spartans endpoint
    * The status code should be 200
    */

    @DisplayName("GET - All spartans")
    @Test
    public void getAllSpartans(){
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");

        assertEquals(200, response.getStatusCode());

        response.prettyPrint();
    }


    /*
     * Given Accept content type is application/xml
     * When User send GEt request to /api/spartans/10 endpont
     * Then status code should be 406
     * And response content type must be application/xml;charset=UTF-8;
     */

    @DisplayName("Accept, application/xml - 406")
    @Test
    public void negativeTest(){
        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans/10");

        response.prettyPrint();

        // Then status code should be 406
        assertEquals(406, response.getStatusCode());

        //And response content type must be "application/xml;charset=UTF-8";
        assertEquals("application/xml;charset=UTF-8", response.contentType());

    }


}
