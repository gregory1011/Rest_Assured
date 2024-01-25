package com.cydeo.LMS_videos.Day01_API_introduction;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class simpleGetRequest {

    String url = "http://3.85.47.176:8000/api/spartans";


    /*
    When user send request to = api/spartans  end point
    Then user should be able to see status code is 200
        and Print out response body into screen.
     */

    @Test
    public void simpleGetRequest(){

        Response response = RestAssured.get(url);

        // both have same result, they get the response status code
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        // verify that status code is 200
        int actualStatusCode = response.statusCode();

        // assert that it is 200
        Assertions.assertEquals(200, actualStatusCode);


        // how to print JSon response body on console
        response.prettyPrint();


    }



}
