package com.cydeo.Homework;

import com.cydeo.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Homework05 extends HrTestBase {


    @Test @DisplayName("Task 1")
    public void createRegion() {

                    /*
                        —> POST a region then do GET same region to do validations.
                         Please use Map or POJO class, or JsonPath
                          Given accept is json
                            And content type is json
                            When I send post request to "/regions/" With json:
                            {
                            "region_id":100,
                            "region_name":"Test Region"
                            }
                            Then status code is 201
                            And content type is json
                            And region_id is 100
                            And region_name is Test Region
                            —> GET
                            Given accept is json
                            When I send GET request to "/regions/100"
                            Then status code is 200
                            And content type is json
                            And region_id is 100
                            And region_name is Test Region

                    */


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("region_id", 1234);
        requestBody.put("region_name", "Abhazia");


        RestAssured.given().accept(ContentType.JSON)  // --> get json format request
                .contentType(ContentType.JSON) // --> send Json format body
                .body(requestBody)
                .when().post("/regions").prettyPeek();


    }
}
