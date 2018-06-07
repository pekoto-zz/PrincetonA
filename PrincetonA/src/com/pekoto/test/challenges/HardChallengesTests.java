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
    
    @Test
    public void testGetRandomSet() {
        int []  arr = {2, 5, 10, 21, 100, 20, 3, 50, 9, 0, 22, 1};
        
        int [] randomSubset = HardChallenges.getRandomSet(arr, 4);
        
        for(int i = 0; i < randomSubset.length; i++) {
            System.out.print(randomSubset[i] + ", ");
        }
        
        System.out.println();
    }
}
