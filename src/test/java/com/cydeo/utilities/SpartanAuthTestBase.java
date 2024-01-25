package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {
        // String url = "http://3.86.111.97:7000";
        RestAssured.baseURI = "http://3.86.111.97:7000";

    }






}
