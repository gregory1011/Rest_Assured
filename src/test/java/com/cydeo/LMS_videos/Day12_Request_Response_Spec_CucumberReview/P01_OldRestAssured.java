package com.cydeo.LMS_videos.Day12_Request_Response_Spec_CucumberReview;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P01_OldRestAssured extends SpartanNewTestBase {

    @Test @DisplayName("Hard assertion")
    public void getAllSpartans() {

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .body("id[1]", is(2))
                .log().body();
    }

    @Test @DisplayName("Soft assertion")
    public void getAllSpartanOldWay(){


        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
        .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .body("id[1]", is(2))
        .when()
                .get("/spartans");

         /*
          OLD WAY --> EXPECT()
         -It works like soft assertion
         NEW WAY --> then() (this is one that we are using)
        -It works like hard assertion
  */

    }


}
