package com.cydeo.LMS_videos.Day11_Junit5Annotation_DDT_ExcelUtil;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class P07_ExcelUtilPractice {

    @Test
    public void test1() {

        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/Library.xlsx",
                "Library1");

        System.out.println("excelUtil.columnCount() = " + excelUtil.columnCount());

        for (Map<String, String> eachRow : excelUtil.getDataList()) {
            System.out.println("eachRow = " + eachRow);
        }


        
    }


}
