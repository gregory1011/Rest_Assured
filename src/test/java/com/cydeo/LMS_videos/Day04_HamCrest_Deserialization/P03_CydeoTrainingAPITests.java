package com.cydeo.LMS_videos.Day04_HamCrest_Deserialization;

import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {

    /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222

     */

    @Test @DisplayName("GET Cydeo student/2")
    public void Test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");

      //  response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals("envoy", response.header("server"));


        JsonPath jsonPath = response.jsonPath();

//        firstName Mark              -->students[0].firstName
//        batch 13                    -->students[0].batch
//        major math                  -->students[0].major
//        emailAddress mark@email.com -->students[0].contact.emailAddress
//        companyName Cydeo           -->students[0].company.companyName
//        street 777 5th Ave          -->students[0].company.address.street
//        zipCode 33222               -->students[0].company.address.zipCode

//        firstName Mark
        String name = jsonPath.getString("students[0].firstName");
        System.out.println("name = " + name);

        assertEquals("Mark", name);

//        batch 13
        int batch = jsonPath.getInt("students[0].batch");
        System.out.println("batch = " + batch);
        assertEquals(13, batch);

//        major math
        String major = jsonPath.getString("students[0].major");
        System.out.println("major = " + major);
        assertEquals("math", major);

//        emailAddress mark@email.com
        String email = jsonPath.getString("students[0].contact.emailAddress");
        System.out.println("email = " + email);
        assertEquals("mark@email.com", email);

//        companyName Cydeo
        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println("companyName = " + companyName);
        assertEquals("Cydeo", companyName);

//        street 777 5th Ave
        String streetName = jsonPath.getString("students[0].company.address.street");
        System.out.println("streetName = " + streetName);
        assertEquals("777 5th Ave", streetName);

//        zipCode 33222
        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        System.out.println("zipCode = " + zipCode);

        assertEquals(33222, jsonPath.getInt("students[0].company.address.zipCode"));


    }


    /*
   TASK
   Given accept type is application/json
   And path param is 22
   When user send request /student/batch/{batch}
   Then status code should be 200
   And content type is application/json;charset=UTF-8
   And Date header is exist
   And Server header is envoy
   And verify all the batch number is 22
    */
    @Test @DisplayName("GET-Cydeo student/22")
    public void Test2(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("batch", 22)
                .when().get("/student/batch/{batch}");

      //  response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals("envoy", response.header("Server"));

        JsonPath jsonPath = response.jsonPath();

        int batch = jsonPath.getInt("students[0].batch");
        System.out.println("batch = " + batch);
        assertEquals(22, jsonPath.getInt("students[0].batch"));
        

    }




}
