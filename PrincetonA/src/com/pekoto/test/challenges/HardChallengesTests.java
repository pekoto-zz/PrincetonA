package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.pekoto.challenges.BiNode;
import com.pekoto.challenges.HardChallenges;
import com.pekoto.challenges.HardChallenges.NodePair;
import com.pekoto.challenges.HeightWeight;
import com.pekoto.challenges.LengthComparator;

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
    
        int[] arr2 = {0, 1, 0, 1, 0};
        
        assertEquals(0, HardChallenges.findMajorityElement(arr2));
    }
    
    @Test
    public void testConvertToLinkedList() {
       
        BiNode[] nodes = new BiNode[7];
        
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new BiNode(i);
        }
        
        nodes[4].node1 = nodes[2];
        nodes[4].node2 = nodes[5];
        nodes[2].node1 = nodes[1];
        nodes[2].node2 = nodes[3];
        nodes[5].node2 = nodes[6];
        nodes[1].node1 = nodes[0];
        
        NodePair result = HardChallenges.convertToLinkedList(nodes[4]);
        
        result.printLinkedListTree(result.next);
    }
    
    @Test
    public void testSortStringsByLength() {
        String [] arr = { "1", "22", "333" };
        
        Arrays.sort(arr, new LengthComparator());
        
        assertEquals("333", arr[0]);
        assertEquals("22", arr[1]);
        assertEquals("1", arr[2]);
    }
    
    @Test
    public void testGetLongestWordFromList() {
        String [] arr = {
                "dog",
                "cat",
                "walker",
                "dogwalker",
                "sun",
                "dress",
                "sundress"
        };
        
        assertEquals("dogwalker", HardChallenges.getLongestWordFromList(arr));
    }
    
    @Test
    public void testGetMaxSumNonAdjacent() {
        int [] arr = { 30, 15, 60, 75, 45, 15, 15, 45 };
        
        assertEquals(180, HardChallenges.getMaxSumNonAdjacent(arr));
    }
    
    @Test
    public void testSearchBigStringForSmalls() {
    	String big = "mississippi";
    	String [] smalls = { "is", "ppi", "hi", "sis", "i" };
    
    	HashMap<String, ArrayList<Integer>> locations = HardChallenges.searchBigStringForSmalls(big, smalls);
    	
    	assertEquals(Integer.valueOf(8), locations.get("ppi").get(0));
    	
    	assertEquals(Integer.valueOf(1), locations.get("i").get(0));
    	assertEquals(Integer.valueOf(4), locations.get("i").get(1));
    	assertEquals(Integer.valueOf(7), locations.get("i").get(2));
    	assertEquals(Integer.valueOf(10), locations.get("i").get(3));
    	
    	assertEquals(Integer.valueOf(1), locations.get("is").get(0));
    	assertEquals(Integer.valueOf(4), locations.get("is").get(1));
    	
    	assertEquals(Integer.valueOf(3), locations.get("sis").get(0));
    }
}
