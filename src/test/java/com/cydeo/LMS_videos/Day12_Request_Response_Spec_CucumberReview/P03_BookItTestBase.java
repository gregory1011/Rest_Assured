package com.cydeo.LMS_videos.Day12_Request_Response_Spec_CucumberReview;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
public class P03_BookItTestBase extends BookItTestBase {


    /**
     * Send a request /api/users/me endpoint as teacher
     * verify status code 200
     * content Type will be ContentType.JSON
     *
     */

    @Test
    public void test1(){

        given().log().uri()
                .accept(ContentType.JSON)
                .headers("Authorization", BookItUtils.getTokenByRole("teacher"))
                .when().get("/api/users/me")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    void test2() {

        given()
                .spec(BookItUtils.getReqSpec("teacher"))
        .when()
                .get("/api/users/me")
                .then()
                .spec(BookItUtils.getResSpec(200));
    }

    //Create one req spec for bookitUtils which accepts role
    //Create one res spec for status code and Json verification


}
