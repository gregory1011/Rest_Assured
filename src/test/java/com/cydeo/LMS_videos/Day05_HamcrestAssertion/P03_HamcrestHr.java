package com.cydeo.LMS_videos.Day05_HamcrestAssertion;


import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P03_HamcrestHr extends HrTestBase {


    /*
       Given accept type is Json
       And parameters: q = {"job_id":"IT_PROG"}
       When users sends a GET request to "/employees"
       Then status code is 200
       And Content type is application/json
       Verify
           - each employees has manager
           - each employees working as IT_PROG
           - each of them getting salary greater than 3000
           - first names are .... (find proper method to check list against list)
           - emails without checking order (provide emails in different order,just make sure it has same emails)
           List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");
           "DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"
  */


    @Test @DisplayName("GET employees IT_PROG with hamcrest")
    public void test1() {

        // expected first names
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");


        given().accept(ContentType.JSON)
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("items.manager_id",everyItem(notNullValue()),
                        "items.job_id", everyItem(equalTo("IT_PROG")),
                        "items.salary", everyItem(greaterThan(3000)),
                        "items.first_name",equalTo(names),
                        "items.email", containsInAnyOrder("DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"));

    }



    /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               first region id is 1
               four regions we have
               region names are not null
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4

               print all the regions names
               ...
               ..
               .
    */


    @Test @DisplayName("GET employees")
   public void test2() {


       JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().header("Date", notNullValue())
                .and().body("items[0].region_name", is(equalTo("Europe")),
                        "items[0].region_id", is(1),
                "items", hasSize(4), "items.region_name", everyItem(notNullValue()),
                "items.region_name", containsInAnyOrder("Europe","Americas","Asia","Middle East and Africa"),
                "items.region_id", containsInRelativeOrder(1,2,3,4)).extract().jsonPath();


        List<String> allRegionNames = jsonPath.getList("items.region_name");
        System.out.println("allRegionNames = " + allRegionNames);


        // get regions names from ui or database

        //compare


    }




}
