package com.cydeo.LMS_videos.Day05_HamcrestAssertion;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P01_HamCrestMatcherIntro {

    @Test
    public void numbers(){
        // junit5 assert equals method
        Assertions.assertEquals(9, 6+3);

        // Hamcrest comes from RestAssured libraries

        // this assert does same think
        // Hamcrest Matchers
        assertThat(6+3, Matchers.is(9));
        assertThat(6+3, is(equalTo(9)));
        assertThat(6+3, equalTo(9));


        // negative assertion
        assertThat(5+5, not(9));
        assertThat(5+5, is(not(9)));
        assertThat(5+5, is(not(equalTo(9))));


        assertThat(5+6,is(greaterThanOrEqualTo(11)));
        assertThat(5+6,greaterThan(10));
        assertThat(5+6,lessThan(12));
        assertThat(5+6,lessThanOrEqualTo(11));

    }


    @Test
    public void testStrings() {

        String msg = "API is fun!";

        assertThat(msg, is("API is fun!"));
        assertThat(msg, equalTo("API is fun!"));
        assertThat(msg, equalToIgnoringCase("api is fun!"));

        assertThat(msg, startsWith("API"));
        assertThat(msg, startsWithIgnoringCase("api"));

        assertThat(msg, endsWith("fun!"));
        assertThat(msg, endsWithIgnoringCase("FUN!"));

        assertThat(msg, containsString("is"));
        assertThat(msg, containsStringIgnoringCase("IS"));

        assertThat(msg, not("FUN!"));
        assertThat(msg, is(not("FUN!")));

    }

    @Test
    public void testCollections() {

        List<Integer> numberList = Arrays.asList(2,3,4,77, 44, 23);  // 6 elements
        assertThat(numberList, hasSize(6));

        // how to check 77 is into collection list
        assertThat(numberList, hasItem(77));

        // how to check 44 and 77 is onto the collection list
        assertThat(numberList, hasItems(44, 77,4));

        // loop though each of the element and make sure they are matching with Matchers inside the everyItem
        assertThat(numberList, everyItem(greaterThanOrEqualTo(1)));

        assertThat(numberList, containsInRelativeOrder(2,3,4,77,44,23));
        assertThat(numberList, containsInAnyOrder(2,3,4,23, 44, 77));


    }
}
