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
        for(String str: arr) {
            String key = sortChars(str);
            
            if(!mapList.containsKey(key)) {
                mapList.put(key, new ArrayList<String>());
            }
            
            mapList.get(key).add(str);
        }
        
        // Convert hash map to array
        int index = 0;
        
        for(String key: mapList.keySet()) {
            ArrayList<String> list = mapList.get(key);
            
            for(String str: list) {
                arr[index] = str;
                index++;
            }
        }
    }
    
    private static String sortChars(String s) {
        char[] chrs = s.toCharArray();
        
        Arrays.sort(chrs);
        
        return new String(chrs);
    }
    
    /*
     * Searches for a key in a a data structure where
     * the length isn't available.
     * 
     * Performance: O(log n)
     */
    public static int findInListy(Listy list, int key) {
        int highIndex = 1;
        
        while(list.elementAt(highIndex) != -1 && list.elementAt(highIndex) < key) {
            highIndex *= 2;
        }
        
        // High index will now be either off the end of the array,
        // or greater than our key. So we can use it in a binary search
        
        return findInListy(list, key, highIndex/2, highIndex);
    }
    
    private static int findInListy(Listy list, int key, int left, int right) {
        while(left <= right) {
            int mid = left + (right-left)/2;
            
            if(list.elementAt(mid) == key) {
                return mid;
            } else if (list.elementAt(mid) == -1 || list.elementAt(mid) > key) {
                // If -1, we've gone of the end of the array, so search left
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return -1;
    }
    
    /*
     * Search a matrix where rows/columns are
     * sorted in ascending order for a given value.
     * 
     * Point: 1. If our key < min column element,
     *        our key is to the left.
     *        2. If our key is > max column element,
     *        our key is to the right.
     *        3. If our key is < min row element,
     *        our key is in the row above.
     *        4. If our key is > max row element,
     *        our key is in the row below.
     *        
     *        So using these rules, start in the top right and move left + down.
     * 
     * Note: There is a more efficient solution that involved
     *       dividing the matrix into quadrants and using
     *       a binary search approach.
     */
    public static boolean sortedMatrixSearch(int [][] matrix, int key) {
        return sortedMatrixSearch(matrix, key, 0, matrix[0].length-1);
    }
    
    private static boolean sortedMatrixSearch(int [][] matrix, int key, int row, int col) {
        
        while(col >= 0 && row < matrix.length) {
            if(matrix[row][col] == key) {
                return true;
            } else if (key < matrix[row][col]) {
                // Key is < min col value, move left
                col--;
            } else {
                // Key is > row val, move down
                row++;
            }
        }
        
        return false;
    }
}
