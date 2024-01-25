package com.cydeo.LMS_videos.Day07_Serialization_POST_PUT_PATCH_DEL;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P03_Spartan_PUT_PATCH_DELETE extends SpartanTestBase {


    @Test @DisplayName("PUT Spartan with Map ( UPDATE )")
    public void test1() {

        //we can provide json request body with map,pojo,string all is valid here too.

        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","John Doe PUT");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone","8877445596");

        //PUT will update existing record, so we choose one of the existing ID,
        // make sure it exist in your IP address
        int id = 115;

        given().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBodyMap) // this will be deserialized
                .when().put("/api/spartans/{id}")  // put() method
                .then()
                .statusCode(204);

    }

    @Test @DisplayName("PATCH Spartan with Map ( UPDATE )")
    public void test2() {

        //we can provide json request body with map, pojo, string all is valid here too.

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","John Doe PATCH");


        //PUT will update existing record, so we choose one of the existing ID,
        // make sure it exist in your IP address
        int id = 116;

        given().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBodyMap) // this will be deserialized
                .when().patch("/api/spartans/{id}") // patch() method
                .then()
                .statusCode(204);

    }

    @Test @DisplayName("DELETE Spartan")
    public void test3() {

        //we can delete one id only one time, so it will give 204 only for the first execution
        int id = 118;

        given()
                .pathParam("id", id)
                .when().delete("/api/spartans/{id}")  // delete() method
                .then().statusCode(204);


        //after deleted when we send get request to id that we deleted, it needs to give 404
        given()
                .pathParam("id",id)
                .when().get("/api/spartans/{id}")// we want to get() response
                .then().statusCode(404);

    }




}
