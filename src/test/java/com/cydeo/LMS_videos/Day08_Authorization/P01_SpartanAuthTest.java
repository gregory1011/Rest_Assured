package com.cydeo.LMS_videos.Day08_Authorization;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {


    @Test @DisplayName("GET /api/spartans as GUEST user --> EXPECT -->401")
    void test1() {

        given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().log().all()
                .statusCode(401)
                .body("error", is("Unauthorized"));

    }


    @Test @DisplayName("GET /api/spartans as GUEST user --> EXPECT -->401")
    public void test2(){
        given().accept(ContentType.JSON)
                .auth().basic("user", "user")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();

    }


    @Test @DisplayName("DELETE /api/spartans/{id} as EDITOR --EXPECT --> 403 FORBIDDEN")
    public void test3(){

        given().log().uri().accept(ContentType.JSON)
                .pathParam("id", 98)
                .auth().basic("editor", "editor")
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(403)
                .body("error", is("Forbidden"))
                .log().all();

    }


    @DisplayName("DELETE /api/spartans/{id} as ADMIN --EXPECT --> 204")
    @Test
    public void test4(){

        given().log().uri()
                .pathParam("id", 100)
                .auth().basic("admin", "admin")
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }



    /********
     *  HOMEWORKS
     *  	Role Based Control Test --> RBAC
     * 			ADMIN  -->  GET  POST PUT PATCH  DELETE   --> Spartan Flow
     * 			EDITOR -->  GET  POST PUT PATCH   403
     * 			USER   -->  GET  403  403  403    403
     * 			GUEST  -->  401  401  401  401    401
     *
     *   -- Create RBAC Test for all different roles from Spartan Application
     *      including with Negative Test cases
        public static void  GETSpartans(String role,String password,int statusCode,int id){
     *
     *                 given().pathParam("id",id)
     *                 .auth().basic(role,password).
     *                 when().get("/api/spartans/{id}").then().statusCode(statusCode);
     *
     *               }
     *
     ***/


}
