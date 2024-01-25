package com.cydeo.LMS_videos.Day10_xmlPathSchema;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class P03_responseTime extends SpartanTestBase {

    @Test
    void test1() {

        Response response = given().accept(ContentType.JSON)
                .auth().basic("user", "user")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // .time(lessThan(1500L))
                // .time(greaterThan(500L));
                .time(both(lessThan(1500L)).and(greaterThan(500L)))
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());
    }


}
