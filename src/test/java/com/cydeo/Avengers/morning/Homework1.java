package com.cydeo.Avengers.morning;


import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class Homework1 extends HrTestBase {

    /*
    * Task 1 :
- Given accept type is Json
- When users sends request to /countries/US
- Then status code is 200
- And Content - Type is application/json
- And response contains United States of America
    */


    @Test @DisplayName("Task1")
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().get("http://3.85.47.176:1000/ords/hr/countries/US");


        response.asPrettyString();

        assertEquals(200, response.getStatusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.asString().contains("United States of America"));

    }




    @Test @DisplayName("Task2")
   public void test2() {
//        Task 2 :
//        - Given accept type is Json
//        - When users sends request to /employees/1 - Then status code is 404

        Response response = given().accept(ContentType.JSON)
                .when().get("/employees/1");

       // response.prettyPrint();

        assertEquals(404, response.statusCode());

    }


    @Test @DisplayName("Task3")
    public void name() {
        /*
        Task 3 :
        - Given accept type is Json
        - And path param regionID value is 1
        - When users sends request to /regions/{regionID}
        - Then status code is 200
        - And Content - Type is application/json
         - And header should contains Date
        - And Transfer-Encoding should be chunked
        - And response region_name Europe
        - And Third link rel is "describedby"
          */


        Response response = given().accept(ContentType.JSON).
                and().pathParam("region_id", 1)
                .when().get("/regions/{region_id}");

      //  response.prettyPrint();

//        - Then status code is 200
        assertEquals(200, response.getStatusCode());

//        - And Content - Type is application/json
        assertEquals(ContentType.JSON.toString(), response.getContentType());

//        - And header should contains Date
        assertTrue(response.headers().hasHeaderWithName("Date"));

//        - And Transfer-Encoding should be chunked
        assertEquals("chunked", response.getHeader("Transfer-Encoding"));

//        - And response region_name Europe
        assertEquals("Europe", response.path("region_name"));

//        - And Third link rel is "describedby"
        assertEquals("describedby", response.path("links[2].rel"));

        // get lonks details
        List<Map<String, String>> links = response.path("links");
       // System.out.println("links = " + links);

        for (Map<String, String> eachLink : links) {
            System.out.println(eachLink);
        }

        // get me last object of the links rel information
        Map<String, String> lastLink = links.get(links.size() - 1);
        String rel = lastLink.get("rel");
        System.out.println("lastRel = " + rel);

        // same result by using response path
        List <Map<String,String>> rels = response.path("links.rel");
        System.out.println("LastRELS = " + rels);

        // find all href endswith regions/1 by using links arrayList and stream

        List<Map<String, String>> allLinks = response.path("links");
        for (Map<String, String> eachLink : allLinks) {
           // eachLink.get()
        }


    }




}
