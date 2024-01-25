package com.cydeo.LMS_videos.Day10_xmlPathSchema;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_MovieXML {

    @Test
    void tes1() {

        Response response = given().queryParam("apikey", "a3ba899")
                .queryParam("r", "xml")
                .queryParam("t", "Inception")
                .when().get("http://www.omdbapi.com").prettyPeek();


        XmlPath xmlPath = response.xmlPath();
        
        // get me attribute
        String titleName = xmlPath.getString("root.movie.@title");
        System.out.println("titleName = " + titleName);

        int year = xmlPath.getInt("root.movie.@year");
        System.out.println("year = " + year);

        String actorsName = xmlPath.getString("root.movie.@actors");
        System.out.println("actorsName = " + actorsName);

    }

    @Test
    void tes2() {

        XmlPath xmlPath = given().queryParam("apikey", "a3ba899")
                .queryParam("r", "xml")
                .queryParam("s", "Harry Potter")
                .when().get("http://www.omdbapi.com").prettyPeek()
                .then()
                .body("root.result.year", everyItem(greaterThan(2000)))
                .body("root.result.title", everyItem(containsString("Harry Potter")))
                .extract().xmlPath();



        // verify totalResult is 134
        System.out.println("totalResult = " + xmlPath.getInt("root.@totalResults"));
        assertThat(134, is(xmlPath.getInt("root.@totalResults")));


        // get all title and make sure it contains Harry Potter
        List<String> allTitles = xmlPath.getList("root.result.@title");
        System.out.println("allTitles = " + allTitles);
        for (String eachTitle : allTitles) {

            assertThat(eachTitle, eachTitle.contains("Harry Potter"));
        }



        // get all years
        List<Integer> allYears = xmlPath.getList("root.result.@year");
        System.out.println("allYears = " + allYears);

        // verify they are greater than 2000
       // assertTrue();

    }



}
