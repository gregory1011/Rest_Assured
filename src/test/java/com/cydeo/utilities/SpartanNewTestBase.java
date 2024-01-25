package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;


public abstract class SpartanNewTestBase {

    public static RequestSpecification requestSpecific;
    public static ResponseSpecification responseSpecific;
    public static RequestSpecification requestUserSpec;

    @BeforeAll
    public static void init() {

        baseURI = "http://3.86.111.97";
        port = 7000;
        basePath ="/api";
    //  baseURI + port + basePath --> "http://3.86.111.97:7000/api"


        requestSpecific = given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        requestUserSpec = given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("user", "user");

        responseSpecific = expect().statusCode(200)
                .contentType(ContentType.JSON);
    }


    //Create dynamic method which is accepting username and password as a parameter and returning
    //request specification dynamicReqSpec()

    public static RequestSpecification dynamicReqSpec(String username,String password){

        return given().
                log().all()
                .accept(ContentType.JSON)
                .auth().basic(username, password);
    }

    //Create dynamic method which has parameter for status code, and returning ResponseSpecification
    // dynamicResSpec()

    public static ResponseSpecification dynamicResSpec(int statusCode){
        return expect().statusCode(statusCode)
                .contentType(ContentType.JSON);

    }


    

}
