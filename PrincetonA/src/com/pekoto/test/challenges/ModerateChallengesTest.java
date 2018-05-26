package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.ModerateChallenges;
import com.pekoto.challenges.SimpleTuple;

/*
 * Unit tests for the ModerateChallenges class.
 */
public class ModerateChallengesTest {

    @Test
    public void testSwapInPlace() {
        SimpleTuple tuple = ModerateChallenges.swapInPlace(4, 2);
        
        assertEquals(2, tuple.getA());
        assertEquals(4, tuple.getB());
    }
}
