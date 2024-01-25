package com.cydeo.LMS_videos.Day08_Authorization;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class P02_BookItTest extends BookItTestBase {

    String email = "lfinnisz@yolasite.com";
    String password = "lissiefinnis";
    String accessToken = BookItUtils.getToken(email, password);



    @Test @DisplayName("Get /api/campuses")
    public void test1() {

        System.out.println("accessToken = " + accessToken);


        given().log().uri().accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);

    }

    // Create new Util class that will generate token based on provided email and password
    // BookItUtils.getToken(String email, String password)


    @Test @DisplayName("GET /api/users/me")
    public void test2() {

        given().log().uri().accept(ContentType.JSON)
                .header("Authorization", BookItUtils.getToken(email, password))
                .when().get("/api/users/me").prettyPeek()
                .then().statusCode(200);

    }


    @Test @DisplayName("GET /api/users/me")
    public void test3() {

        given().log().uri().accept(ContentType.JSON)
                .auth().oauth2(accessToken)
                .when().get("/api/users/me").prettyPeek()
                .then().statusCode(200);

    }


    


}
