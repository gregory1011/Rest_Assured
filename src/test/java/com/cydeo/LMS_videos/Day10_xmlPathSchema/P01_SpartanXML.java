package com.cydeo.LMS_videos.Day10_xmlPathSchema;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_SpartanXML extends SpartanTestBase {

    /**
     * Given accept type is application/xml
     * and basic auth is admin admin
     * When send the request /api/spartans
     * Then status code is 200
     * And content type is application/xml
     *   print first spartan name
     *   .....
     *   ...
     */


    @Test
    void tes1() {

        given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[1].name", is("Nels"));

    }


    @Test @DisplayName("GET /api/spartans with using XML Path")
    void tes2() {

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();
        String firstSpartanName = xmlPath.getString("List.item[0].name");
        System.out.println("firstSpartanName = " + firstSpartanName);
        
        
        // Can we get name from db and compare with this variable?
         //YES
        
        // get me last spartan name
        String lastSpartanName = xmlPath.getString("List.item[-1].name");
        System.out.println("lastSpartanName = " + lastSpartanName);

        // get all the names
        List<String> allNames = xmlPath.getList("List.item.name");
        System.out.println("allNames = " + allNames);


    }

}
