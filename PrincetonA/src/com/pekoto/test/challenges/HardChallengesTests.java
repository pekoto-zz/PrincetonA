package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.HardChallenges;

/*
 * Unit tests for the HardChallenges class
 */
public class HardChallengesTests {

    @Test
    public void testAddWithoutPlus() {
        assertEquals(1433, HardChallenges.addWithoutPlus(759, 674));
    }
}
