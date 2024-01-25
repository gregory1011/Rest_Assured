package com.cydeo.LMS_videos.Day07_Serialization_POST_PUT_PATCH_DEL;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_SpartanPOST extends SpartanTestBase {

    /**
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is 'A Spartan is Born!'
     "name": "John Doe",
     "gender": "Male",
     "phone": 8877445596
     */


    @Test @DisplayName("POST Spartan with String body")
    public void test1() {

// we create one String and put the info that we want to send as a Json request body
        String requestBody = "{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";


        JsonPath jsonPath = given().log().body().accept(ContentType.JSON)  // hey API, send me Json response body
                .and().contentType(ContentType.JSON)   // hey API, I'm sending Json request body
                .body(requestBody)
                .when().post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();


        // request body verification
        assertEquals("John Doe", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(8877445596l, jsonPath.getLong("data.phone"));


        // I want to get ID out of the response body, to delete or send request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("John Doe's id = " + id);


    }

    @Test @DisplayName("POST Spartan with Map body")
    public void test2() {

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "John Doe");
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("phone",8877445596l);

// we create one Map and put the info that we want to send as a Json request body
        System.out.println("requestBodyMap = " + requestBodyMap);



        JsonPath jsonPath = given().log().body().accept(ContentType.JSON)  // hey API, send me Json response body
                .and().contentType(ContentType.JSON)   // hey API, I'm sending Json request body
                .body(requestBodyMap)
                .when().post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();


        // request body verification
        assertEquals("John Doe", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(8877445596l, jsonPath.getLong("data.phone"));


// I want to get ID out of the response body, to delete or send request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("John Doe's id = " + id);


// can we have SpartanUtil class which is giving as a requestMap with dynamic values?
       // using faker Library inside? YES



    }

    @Test @DisplayName("POST Spartan with POJO")
    public void test3() {

        Spartan spartan = new Spartan();  // create Spartan object form Spartan class
        spartan.setName("Polonicus");  // we use setter to set name and others
        spartan.setGender("Male");
        spartan.setPhone(5088900456l);

// we create one Spartan object and set the info that we want to send as a POJO request body
        System.out.println("spartan = " + spartan);

        JsonPath jsonPath = given().log().body().accept(ContentType.JSON)  // hey API, send me Json response body
                .and().contentType(ContentType.JSON)   // hey API, I'm sending Json request body
                .body(spartan)
                .when().post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();


        // request body verification
        assertEquals("Polonicus", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(5088900456l, jsonPath.getLong("data.phone"));


// I want to get ID out of the response body, to delete or send request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("Polonicus id = " + id);


// can we have SpartanUtil class which is giving as a requestMap with dynamic values?
        // using faker Library inside? YES



    }

    @Test @DisplayName("POST Spartan with POJO and GET same Spartan")
    public void test4() {

        Spartan spartanPOST = new Spartan();  // create Spartan object form Spartan class
        spartanPOST.setName("Polonicus");  // we use setter to set name and others
        spartanPOST.setGender("Male");
        spartanPOST.setPhone(5088900456l);
        spartanPOST.setId(500); //even if we put some id value, it didn't serialize because of the jackson annotation on the class


// we create one Spartan object and set the info that we want to send as a POJO request body
        System.out.println("spartan = " + spartanPOST);

        JsonPath jsonPath = given().log().body().accept(ContentType.JSON)  // hey API, send me Json response body
                .and().contentType(ContentType.JSON)   // hey API, I'm sending Json request body
                .body(spartanPOST)
                .when().post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();


        // request body verification
        assertEquals("Polonicus", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(5088900456l, jsonPath.getLong("data.phone"));


// I want to get ID out of the response body, to delete or send request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("Polonicus id = " + id);

        // Send GET request to the Spartan that is created then Deserialize
        // to Spartan class and compare

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().response();
        // get Json response and deserialize
        Spartan spartanGET = response.as(Spartan.class);
        System.out.println("spartanGET = " + spartanGET);


        // verify names that we send is matching
        assertEquals(spartanPOST.getName(), spartanGET.getName());
        assertEquals(spartanPOST.getPhone(), spartanGET.getPhone());


    }






}
