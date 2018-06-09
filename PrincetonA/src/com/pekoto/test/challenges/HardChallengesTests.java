package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
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
        assertEquals(2, HardChallenges.getNumOf2s(12));
    }
    
    @Test
    public void testGetTrueFrequencies() {
        HashMap<String, Integer> nameFrequencies = new HashMap<String, Integer>();
        
        nameFrequencies.put("John", 3);
        nameFrequencies.put("Jonathan", 4);
        nameFrequencies.put("Johnny", 3);
        nameFrequencies.put("Chris", 1);
        nameFrequencies.put("Kris", 3);
        nameFrequencies.put("Brian", 2);
        nameFrequencies.put("Bryan", 4);
        nameFrequencies.put("Carleton", 4);
        
        String [][] synonyms = {
                    {"John", "Jonathan"},
                    {"Jonathan", "Johnny"},
                    {"Chris", "Kris"},
                    {"Brian", "Bryan"}
        };
        
        HashMap<String, Integer> trueFrequencies = HardChallenges.getTrueFrequencies(nameFrequencies, synonyms);
        
        assertEquals(Integer.valueOf(6), trueFrequencies.get("Brian"));
        assertEquals(Integer.valueOf(4), trueFrequencies.get("Kris"));
        assertEquals(Integer.valueOf(4), trueFrequencies.get("Carleton"));
        assertEquals(Integer.valueOf(10), trueFrequencies.get("Jonathan"));
    }
}
