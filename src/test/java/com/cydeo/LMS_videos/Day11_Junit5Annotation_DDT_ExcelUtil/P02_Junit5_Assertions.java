package com.cydeo.LMS_videos.Day11_Junit5Annotation_DDT_ExcelUtil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class P02_Junit5_Assertions {

    /**
     *
     * HARD ASSERT -->
     * - Test Execution will be aborted if the assert condition is not met
     * - Rest of the execution will stop
     * - Use Case --> if we are checking critical functionally of the app we can check with hard assert
     *
     **/


    @Test
    public void hardAssert() {
        assertEquals(10, 5+5);
        System.out.println("----First Assert is done");

        assertEquals(10, 5+5);
        System.out.println("----Second Assert is done");

        assertEquals(10, 5+4); // fail
        System.out.println("----Third Assert is done");

    }


    /**
     * SOFT ASSERT --> VERIFY (Soft Assertion is implementation of VERIFY
     * -Test execution will continue till end of the code fragment even if one the assertion is failing
     *
     *
     */

    @Test @DisplayName("JUNIT 5 Soft Assertion is implemented")
    public void softAssert(){

        assertAll("Learning Soft Assert",
                    ()->assertEquals(10,5+5),
                    ()->assertEquals(10,5+3),
                    ()->assertEquals(10,5+4)
                 );

        assertAll( "Second time",
                ()->assertEquals(10, 5+5),
                ()->assertEquals(10, 8+1),
                ()->assertEquals(3+6, 2));

    }


}
