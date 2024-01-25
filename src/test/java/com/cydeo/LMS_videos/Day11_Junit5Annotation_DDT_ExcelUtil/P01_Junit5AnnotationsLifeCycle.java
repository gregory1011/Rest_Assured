package com.cydeo.LMS_videos.Day11_Junit5Annotation_DDT_ExcelUtil;

import org.junit.jupiter.api.*;

public class P01_Junit5AnnotationsLifeCycle {

    @BeforeAll
    public static void init(){
        System.out.println("------BeforeAll is running-------");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("------BeforeEach is running-------");
    }

    @Test
    public void test1() {
        System.out.println("------Test1 is running-------");
    }

    @Disabled
    @Test
    void test2() {
        System.out.println("------Test2 is running-------");
    }

    @AfterEach
    public void destroyEach(){
        System.out.println("------AfterEach is running-------");
    }
    @AfterAll
    public static void destroy(){
        System.out.println("------AfterAll is running-------");
    }
}
