package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.RecursionAndDynamic;

/*
 * Unit tests for the RecursionAndDynamic class
 */
public class RecursionAndDynamicTests {

    @Test
    public void testCountWays() {
        assertEquals(121415, RecursionAndDynamic.countWays(20));
    }
}
