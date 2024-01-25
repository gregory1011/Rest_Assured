package com.cydeo.LMS_videos.Day11_Junit5Annotation_DDT_ExcelUtil;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class P08_BookitLoginDDTTest {

    //create a method to read bookitqa3 excel file information (QA3 Sheet)
    //use those information as an email and password to send a get request to /sign endpoint
    //verify you got 200 for each request
    //print accessToken for each request


    @ParameterizedTest
    @MethodSource("getExcelData")
    public void test2 ( Map<String, String> userInfo ){


    }

    public static List<Map<String, String>> getExcelData(){

        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");

        return excelUtil.getDataList();
    }


}
