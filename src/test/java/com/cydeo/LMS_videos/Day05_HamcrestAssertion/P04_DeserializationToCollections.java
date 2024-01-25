package com.cydeo.LMS_videos.Day05_HamcrestAssertion;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P04_DeserializationToCollections extends SpartanTestBase {



     /*
     Given accept type is application/json
     And Path param id = 10
     When I send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
         id > 10
         name>Lorenza
         gender >Female
         phone >3312820936
     */


    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}")
                .then()

                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        // if you want to convert Json into Java collection (map, list,custom class,...)
        // THIS is called DESERIALIZATION

        // Solution 1--> using response as method
        
        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);

        int id = (int)spartanMap.get("id");
        System.out.println("id = " + id);

        Object name = spartanMap.get("name");
        System.out.println("name = " + name);


        // Solution 2 --> using response as method

        JsonPath jsonPath = response.jsonPath();
        
        Map<String, Object> jsonPathMap = jsonPath.getMap("");
        System.out.println("jsonPathMap = " + jsonPathMap);

        int idJson = (int) jsonPathMap.get("id");
        System.out.println("idJson = " + idJson);



    }


    @Test @DisplayName("GET all spartans with Java Collection")
   public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then()

                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();


        // Approach 1 --> with response object

        List<Map<String, Object>> spartanList = response.as(List.class);

//        for (Map<String, Object> spartanMap : spartanList) {
//            System.out.println("spartanMap = " + spartanMap);
//        }


        // how to find first spartan info
        System.out.println("spartanList.get(0) = " + spartanList.get(0));  // index 0 from the List

        // how to get first spartan name
        String name = (String) spartanList.get(0).get("name");
        System.out.println("name = " + name);


        // how to get first spartan id
        int id = (int) spartanList.get(0).get("id");
        System.out.println("id = " + id);


        // Approach 2 --> JsonPath
        JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> listSpartan = jsonPath.getList("");

        // how to find first spartan info
        System.out.println("listSpartan.get(0) = " + listSpartan.get(0));

        // how to get first spartan name
        System.out.println("listSpartan.get(0).get(\"name\") = " + listSpartan.get(0).get("name"));

        // how to get first spartan id
        System.out.println("listSpartan.get(0).get(\"id\") = " + listSpartan.get(0).get("id"));


    }




}
