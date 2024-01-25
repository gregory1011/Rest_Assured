package com.cydeo.LMS_videos.Day04_HamCrest_Deserialization;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P02_HrWithJsonPath extends HrTestBase {
    
    @DisplayName("GET all countries")
    @Test
    public void test1(){

        Response response = get("/countries");

      //  response.prettyPrint();

        assertEquals(200, response.statusCode());

        // create JPath object
        JsonPath jsonPath = response.jsonPath();

        // get me 3rd country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " + jsonPath.getString("items[2].country_name"));

        // get me 3rd and 4th country name
        System.out.println("jsonPath.getString(\"items[2,3].country_name\") = " + jsonPath.getString("items[2,3].country_name"));

        // get me  all country name where region_id is 2
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("list = " + list);

    }
    
    
     /*
        Given accept type is application/json
        And query param limit is 200
        When user send request /employees
        Then user should see ............
     */

    @DisplayName("GET all /employees?limit=200 with JsonPath")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", 200)
                .when().get("/employees");

       // response.prettyPrint();

        assertEquals(200, response.statusCode());

        // create JsonPath object in order to retrieve data from HR-Employees table
        JsonPath jsonPath = response.jsonPath();

        // get all emails from response
        List<Object> allEmails = jsonPath.getList("items.email");
        System.out.println("allEmails.size() = " + allEmails.size());
        System.out.println("allEmails = " + allEmails);

        // get all emails who's working as IT_PROG
        List<Object> filterEmails = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println("filterEmails.size() = " + filterEmails.size());
        System.out.println("filterEmails = " + filterEmails);

        // get me all employees FIRST_NAME whose SALARY is more than 10,000
        List<Object> allEmployeesSalaryMore10K = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("allEmployeesSalaryMore10K = " + allEmployeesSalaryMore10K);

        // get me all info from response who has max SALARY
        String maxSalary = jsonPath.getString("items.max {it.salary}");
        System.out.println("maxSalary = " + maxSalary);

        // get me FIRST_NAME who has max SALARY
        String firstNameMaxSalary = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("firstNameMaxSalary = " + firstNameMaxSalary);
        
        // get me FIRST_NAME from response who has min SALARY
        String minSalary = jsonPath.getString("items.min {it.salary}");
        System.out.println("minSalary = " + minSalary);

        String fistNameMinSalary = jsonPath.getString("items.min {it.salary}.first_name");
        System.out.println("fistNameMinSalary = " + fistNameMinSalary);

        double salary = jsonPath.getDouble("items.min{it.salary}.salary");
        System.out.println("salary = " + salary);




    }


     /*

    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locations
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

      */

    @Test @DisplayName("Get all from Locations")
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .and().when().get("/locations");

       // response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

       // get the second city with JsonPath
        String secondCity = jsonPath.getString("items.city[1]");
        System.out.println("secondCity = " + secondCity);
        
        //  get the last city with JsonPath
        String lastCity = jsonPath.getString("items.city");
        System.out.println("lastCity = " + lastCity);

        //  get all country ids
        List<Object> allCountryID = jsonPath.getList("items.findAll {it.country_id}.country_id");
        System.out.println("allCountryID = " + allCountryID);

        //  get all city where their country id is UK
        List<Object> UKCityFilter = jsonPath.getList("items.findAll{it.country_id=='UK'}.city");
        System.out.println("UKCityFilter = " + UKCityFilter);



    }




}
