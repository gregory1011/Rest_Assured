package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class SpartanTestBase {

    public Logger log = LogManager.getLogger(this.getClass());


    @BeforeAll
    public static void init() {
        // String url = "http://3.85.47.176:8000";
        RestAssured.baseURI = "http://54.210.244.9:8000";
    }






}
