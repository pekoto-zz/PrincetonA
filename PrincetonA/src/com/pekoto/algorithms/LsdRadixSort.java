package com.pekoto.algorithms;

/**
 * Least Signficant Digit (LSD) Sort, a kind of radix sort.
 * Sorts an array of items from their least significant digit (at the right),
 * to their most significant digit (on the left.
 * 
 * This is essentially a KeyIndexCount repeated from right > left for
 * all items in the array. Since KeyIndexCount is stable, the items will
 * be gradually sorted.
 * 
 * 1. For each element in the input array...
 * 2. Start at the right-most (least significant digit) and work backwards
 * 3. Sort the array using a KeyIndexCount for that char:
 * 	3.1 Count the number of times that char appears in countArr[val+1]
 * 	3.2 Calculate the cumulative counts -- the number of elements that appear before this element
 * 		(i.e., where this element should start being inserted in a sorted array)
 *  3.3 Go through the main array, reading the char, putting it into the index indicated by the countArr
 *  3.4 Increment the count array after use -- get next insertion position
 *  3.5 Copy the auxiliary array back to the input array
 * 4. Repeat with the next char
 * 
 * Performance: O(nw) -- n = no. of keys, w = length
 * Space: O(n+w) -- aux array for copying and storing element counts
 * 
 */
public class LsdRadixSort {
  
  // 256 chars in extended ASCII
	private static final int RADIX_SIZE = 256;
	
	public static void sort(String [] arr, int len) {
		
		// Go through each char in the string, starting from the last char
		// and sort by that char
		for(int charPos = len-1; charPos >= 0; charPos--) {
			
			int [] charCounts = new int[RADIX_SIZE + 1];
			
			// 1. Count the num of chars at each position
			for(int i = 0; i < arr.length; i++) {
				int chr = arr[i].charAt(charPos);
				charCounts[chr+1]++;
			}
			
			// 2. Calculate cumulative count of the char
			for(int i = 1; i < charCounts.length; i++) {
				charCounts[i] += charCounts[i-1];
			}
			
			// 3. Copy to auxiliary array
			String [] auxArr = new String[arr.length];
			
			for(int i = 0; i < arr.length; i++) {
				int chr = arr[i].charAt(charPos);
				int auxIndex = charCounts[chr];
				auxArr[auxIndex] = arr[i];
				charCounts[chr]++;
			}
			
			// 4. Copy back to main array
			for(int i = 0; i < arr.length; i++) {
				arr[i] = auxArr[i];
			}
		}
	}
}
