package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
    
    @Test
    public void testGetAllSubarrays() {
        int [] arr = {1, 2, 3, 4, 5};
        
        List<int[]> subarrays = HardChallenges.getAllSubarrays(arr);
        
        for(int[] subarray: subarrays) {
            for(int i = 0; i < subarray.length; i++) {
                System.out.print("[" + subarray[i] + "]");
            }
            System.out.println();
        }
    }
    
    @Test
    public void testGetNumOf2s() {
        assertEquals(1, HardChallenges.getNumOf2s(12));
    }
}
