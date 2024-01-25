package com.cydeo.LMS_videos.Day02_Headers_Parameters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Spartan_Get_Request {


    String url = "http://3.85.47.176:8000";


    /*
     * Given content type is application.json
     * When users sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

    @Test
    public void getAllSpartans(){

        Response response = RestAssured.given()
                .accept(ContentType.JSON)  // hey  api sent me json response
                .when()
                .get(url + "/api/spartans");

        //print the response body
  //      response.prettyPrint();

        // verification status code
        int actualStatusCode = response.statusCode();  // 200

        Assertions.assertEquals(200, actualStatusCode);

        // how to get response content type header?
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        // assert the content type
        Assertions.assertEquals("application/json", contentType);

        // get connection header
        System.out.println("response.getHeader = " + response.getHeader("Content-type"));
        System.out.println("response.headerConnection= " + response.header("Connection"));
        System.out.println("response.headerDate = " + response.header("Date"));
        
        // how to verify header exists
        // hasHeadersWithName it is useful for dynamic header value like date
        boolean date = response.headers().hasHeaderWithName("Transfer-Encoding");
        System.out.println("date = " + date);
        Assertions.assertTrue(date);  // assert is header value exists

    }


    /*
    *  Given content type is application/jason
    *  When user sends GET request /api/spartans/3 endpoint
    *  The status code should be 200
    *  And Content type should be application/json
    *  And response body needs tot contains Fidole
    */

    @Test
    public void Test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(url + "/api/spartans/3");

        int statusCode = response.getStatusCode();
        System.out.println( "StatusCode = " + response.getStatusCode() );
        Assertions.assertEquals(200, statusCode );

        String contentType = response.getContentType();
        System.out.println("ContentType = " + response.getContentType());
        Assertions.assertEquals("application/json", contentType);

        Assertions.assertEquals("application/json", response.header("Content-Type"));
 // or Assertions.assertEquals(ContentType.JSON.toString, response.header("Content-Type"));

        response.prettyPrint();

        Assertions.assertTrue( response.body().asString().contains("Fidole") );  // true or false

        // get id value


    }


   /*
    * Given no headers provided
    * When Users send GET request to /api/hello
    * The response status should be 200
    * And Content type header should be "text/plain;charset=UTF-8"
    * And header should contain Date
    * And Content-Length should be 17
    * And body should be"Hello from Sparta"
    */

    @Test
    public void Test3(){
        Response response = RestAssured.when().get(url + "/api/hello");
        //print result on the console
        response.prettyPrint();

        // verify status code
        Assertions.assertEquals(200, response.getStatusCode());

        // and Content type header should be "text/plain;charset=UTF-8"
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        // And header should contain Date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //And Content-Length should be 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        // And body should be"Hello from Sparta"
        Assertions.assertTrue( response.body().asString().equals("Hello from Sparta") );


    }

}
