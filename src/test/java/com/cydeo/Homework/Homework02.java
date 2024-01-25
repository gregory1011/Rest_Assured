package com.cydeo.Homework;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class Homework02 extends HrTestBase {


    /*
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
And Region_id is 2
    */

    @Test @DisplayName("Task1")
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country_id", "US")
                        .when().get("/countries");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());



    }




}
