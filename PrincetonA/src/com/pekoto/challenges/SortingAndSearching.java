package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SortingAndSearching {

    /*
     * Merges together 2 sorted arrays, a and b, where
     * a contains enough buffer space at the end to fit b.
     * 
     * Point: To save having to shift elements in a, merge
     * from the end of the array.
     */
    public static void mergeSorted(int [] a, int [] b, int endOfA) {
        
        int nextElemIndex = endOfA + b.length - 1;
        int aIndex = endOfA-1;
        int bIndex = b.length-1;
        
        while(bIndex >= 0) {
            if(aIndex != 0 && a[aIndex] >= b[bIndex]) {
                a[nextElemIndex--] = a[aIndex--];
            } else {
                a[nextElemIndex--] = b[bIndex--];
            }
        }
    }
    
    /*
     * Rearranges an array so that strings that
     * are anagrams of each other end up next
     * to each other.
     * 
     * Performance: O(n log n) -- or, I suppose,
     *              O(n * s log s), where s is the len of the string
     *              and n is the number of strings?
     */
    public static void sortAnagrams(String [] arr) {
        HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();
        
        // Group words by anagram
        for(String s: arr) {
            String key = sortChars(s);
            
            if(!mapList.containsKey(key)) {
                mapList.put(key, new ArrayList<String>());
            }
            
            mapList.get(key).add(s);
        }
        
        // Convert hash map to array
        int index = 0;
        
        for(String key: mapList.keySet()) {
            ArrayList<String> list = mapList.get(key);
            
            for(String t: list) {
                arr[index] = t;
                index++;
            }
        }
    }
    
    private static String sortChars(String s) {
        char[] chrs = s.toCharArray();
        
        Arrays.sort(chrs);
        
        return new String(chrs);
    }
}
