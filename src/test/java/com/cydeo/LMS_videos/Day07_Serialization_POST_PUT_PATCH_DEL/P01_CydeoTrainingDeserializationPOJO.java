package com.cydeo.LMS_videos.Day07_Serialization_POST_PUT_PATCH_DEL;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {


    @Test
    @DisplayName("GET Cydeo student/2")
    public void Test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("students[0].firstName");
        assertEquals("Mark", name);

        int batch = jsonPath.getInt("students[0].batch");
        assertEquals(13, batch);

        String major = jsonPath.getString("students[0].major");
        assertEquals("math", major);

        String email = jsonPath.getString("students[0].contact.emailAddress");
        assertEquals("mark@email.com", email);

        String companyName = jsonPath.getString("students[0].company.companyName");
        assertEquals("Cydeo", companyName);

        String streetName = jsonPath.getString("students[0].company.address.street");
        assertEquals("777 5th Ave", streetName);

        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        assertEquals(33222, jsonPath.getInt("students[0].company.address.zipCode"));


        System.out.println("------------------------STUDENT CLASS-------------------------");

        // Deserialize to Student class
        Student student = jsonPath.getObject("students[0]", Student.class);
        System.out.println("student.getFirstName() = " + student.getFirstName());
        assertEquals("Mark", student.getFirstName());

        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());
        assertEquals("mark@email.com", student.getContact().getEmailAddress());



    }



    @Test
    @DisplayName("GET Cydeo students")
    public void Test2() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("students[0].firstName");
        assertEquals("Mark", name);

        int batch = jsonPath.getInt("students[0].batch");
        assertEquals(13, batch);

        String major = jsonPath.getString("students[0].major");
        assertEquals("math", major);

        String email = jsonPath.getString("students[0].contact.emailAddress");
        assertEquals("mark@email.com", email);

        String companyName = jsonPath.getString("students[0].company.companyName");
        assertEquals("Cydeo", companyName);

        String streetName = jsonPath.getString("students[0].company.address.street");
        assertEquals("777 5th Ave", streetName);

        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        assertEquals(33222, jsonPath.getInt("students[0].company.address.zipCode"));


        System.out.println("------------------------STUDENT's CLASS-------------------------");

        // Deserialize to Student class
        Students students = jsonPath.getObject("", Students.class);
        System.out.println("students = " + students);

        // we deserialize everything to Student class which is holding List of Student
        Student student = students.getStudents().get(0);

        // if there is no path, we can use response.as method for deserialization
        // Students studentsWithAS = response.as(Students.class);


    }


}
