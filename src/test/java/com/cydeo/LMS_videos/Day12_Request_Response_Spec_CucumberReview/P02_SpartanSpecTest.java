package com.cydeo.LMS_videos.Day12_Request_Response_Spec_CucumberReview;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class P02_SpartanSpecTest extends SpartanNewTestBase {

    @Test
    @DisplayName("Hard assertion")
    public void getAllSpartans() {

        given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON);


//        RequestSpecification requestSpecif = given()
//                .log().all()
//                .accept(ContentType.JSON)
//                .auth().basic("admin", "admin");
//
//        ResponseSpecification responseSpecif = expect().statusCode(200)
//                .contentType(ContentType.JSON);

    }

    @Test
    public void getAllSpartansWith_reqSpec_respSpec() {

        given()
                .spec(requestSpecific)
        .when()
                .get("spartans")
        .then()
                .spec(responseSpecific);
    }


    @Test
    void getAllSpartanWithReqResSpec() {

        given()
                .spec(requestSpecific)
                .and()
                .pathParam("id", 10)
          .when()
                .get("/spartans/{id}")
          .then()
                .spec(responseSpecific)
                .body("id", is(10));

    }


    @Test
    void getAllSpartanAsUser() {

        given()
                .spec(requestUserSpec)
                .and()
                .pathParam("id", 10)
        .when()
                .get("/spartans/{id}")
        .then()
                .spec(responseSpecific)
                .body("id", is(10));

    }


    @Test
    void getAllSpartanWithDynamicSpecs() {

        given()
                .spec(dynamicReqSpec("user", "user"))
                .and()
                .pathParam("id", 10)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(dynamicResSpec(200))
                .body("id", is(10));

    }


    /**
     *    Create GET_RBAC.csv
     *    username,password,id,statusCode
     *    admin,admin,3,200
     *    editor,editor,3,200
     *    user,user,3,200
     *
     *   Create a parameterized test to check RBAC for GET method
     **/

    @ParameterizedTest
    @CsvFileSource (resources = "/GET_RBAC.csv", numLinesToSkip = 1)
    void getSingleSpartan_GET_RBAC (String username,String password,int id,int statusCode) {


        given().log().uri()
                .spec(dynamicReqSpec(username, password))
                .pathParam("id", id)
                .when().get("/spartans/{id}")
                .then()
                .spec(dynamicResSpec(statusCode));
    }


    /**
     *  Create DELETE_RBAC.csv
     *   username,password,id,statusCode
     *    editor,editor,3,403
     *    user,user,3,403
     *    admin,admin,3,204
     *
     *  Create a parameterized test to check RBAC for DELETE method
     **/

    @ParameterizedTest
    @CsvFileSource (resources = "/DELETE_RBAC.csv",  numLinesToSkip = 1)
    void deleteSingleSpartan_DELETE_RBAC(String username, String password, int id, int statusCode) {

    given().log().uri()
            .spec(dynamicReqSpec(username, password))
            .pathParam("id", id)
    .when().delete("/spartans/{id}")
            .then()
            .spec(dynamicResSpec(statusCode));
    }



}
