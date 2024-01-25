package com.cydeo.Homework;

import com.cydeo.Homework.pojos.Driver;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Homework04  {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://ergast.com/api/f1";
    }

    @Test @DisplayName("GET")
    public void test1() {

        String isJason = ".json";

        Response response  = given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}" + isJason)
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", is("Fernando"))
               // .body("MRData.DriverTable.Drivers[0].familyName", is("Alonso"))
               // .body("MRData.DriverTable.Drivers[0].nationality", is("Spanish"))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String nationality = jsonPath.getString("MRData.DriverTable.Drivers[0].nationality");
        Assertions.assertEquals("Spanish", nationality);

        String familyName = response.path("MRData.DriverTable.Drivers[0].familyName");
        Assertions.assertEquals("Alonso", familyName);


        //First I need to get all response as Map
        Map<String,Object> responseMap=jsonPath.getMap("");

        //if we want to get value of MRData we need to use get method
        Map<String,Object> mrDataMap = (Map<String, Object>) responseMap.get("MRData");
        System.out.println("mrData = " + mrDataMap);

        //if we want to get any value from map we need to use the key
        String total = (String) mrDataMap.get("total");

        System.out.println("total = " + total);

        //  we want to get DriverTable key's value
        Map<String,Object> driverTable = (Map<String, Object>) mrDataMap.get("DriverTable");
        System.out.println("driverTable = " + driverTable);

        //we want to get all drivers from map
        List<Map<String,Object>> drivers= (List<Map<String, Object>>) driverTable.get("Drivers");

        System.out.println("drivers = " + drivers);

        //we want to get the first driver from list
        Map<String,Object> driver = drivers.get(0);

        //we want to get family name from driver map
        System.out.println("familyName = " + driver.get("familyName"));
    }


    @Test @DisplayName("GET")
    public void test2() {

        String isJason = ".json";

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}" + isJason)
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is("1"))
                .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Driver driver = jsonPath.getObject("MRData.DriverTable.Drivers[0]", Driver.class);

        System.out.println("driver.getGivenName() = " + driver.getGivenName());
        System.out.println("driver.getNationality() = " + driver.getNationality());
    }



}
