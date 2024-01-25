package com.cydeo.LMS_videos.Day13_log4j_SOAP_sample_MOCK_API;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanMock extends SpartanTestBase{


//    @BeforeAll
//    public static void init() {
//
//        baseURI = "https://d1a6cbb7-8f62-4542-b046-cfe95b366a08.mock.pstmn.io";
//       // baseURI = "https://cfd30a3e-1b44-4b0a-b289-f1055e219b16.mock.pstmn.io";
//    }


    @Test @DisplayName("GET /api/hello")
    public void GetTest1(){

        Response response = given().accept(ContentType.TEXT)
                .log().all()
                .when().get("/api/hello")
                .then().statusCode(200)
                .extract().response();

        assertEquals("Hello from Sparta", response.asString());
    }


    @Test @DisplayName("GET /api/spartans")
    public void GetTest2(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        response.prettyPrint();
    }

}
