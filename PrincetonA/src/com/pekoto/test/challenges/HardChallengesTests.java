package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.pekoto.challenges.HardChallenges;
import com.pekoto.challenges.HeightWeight;

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
    
    @Test
    public void testGetLongestIncreasingSubsequence() {
        ArrayList<HeightWeight> arr = new ArrayList<HeightWeight>();
        
        arr.add(new HeightWeight(65, 60));
        arr.add(new HeightWeight(70, 150));
        arr.add(new HeightWeight(56, 90));
        arr.add(new HeightWeight(60,  95));
        arr.add(new HeightWeight(68, 110));
        arr.add(new HeightWeight(35, 65));
        arr.add(new HeightWeight(40, 60));
        arr.add(new HeightWeight(45, 63));
        
        ArrayList<HeightWeight> longest = HardChallenges.getLongestIncreasingSubsequence(arr);
        
        assertEquals(6, longest.size());
    }
    
    @Test
    public void testFindMajorityElement() {
        int[] arr = {0, 0, 1, 2, 2, 0, 1, 0, 1, 1, 1, 1, 1};
    
        assertEquals(1, HardChallenges.findMajorityElement(arr));
    }
}
