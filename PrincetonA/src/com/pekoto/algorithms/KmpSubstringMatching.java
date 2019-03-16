package com.pekoto.algorithms;

/*
 * The Knuth-Morris-Pratt substring matching algorithm.
 * 
 * The problem with brute force substring pattern matching algorithms
 * is that we often end up backtracking along the string. With KMP,
 * we instead backtrack along the pattern.
 * 
 * We build a pattern matching array that tells us where to jump back
 * to in the pattern if we get a mismatch. (We always jump forward when we match.)
 * Think of it like a FSA, with each element telling us where to jump to next.
 * 
 * Importantly, this means we could run substring matching on an infinite stream of text,
 * or a stream of text that is too large to fit in memory.
 * 
 * First, build the FSA:
 * 1. Set up an array the size of the substring, index = 0, i = 1
 * 2. If the chars are the same, increment index, set the value at i to be index, increment i
 * 3. Else, if mismatch, if index = 0, just increment i
 * 						 else, set index to be arr[index-1]
 * 
 * Think about it, this is like jumping back:
 * 
 * 0 1 2 3 4 5
 * A B C A B Y
 * 0 0 0 1 2 0
 * 
 * If we had A B C A B [D], then when we hit D, we look back to B
 * and try start matching from index 2 [C]. If the next char wasn't C,
 * then we'd start trying to match from 0 again.
 * 
 * Then, match the string against the pattern:
 * 1. While the strIndex < str && ptnIndex < ptn...
 * 2. If the chars at indexes match, increment both
 * 3. Else, if mismatch, if ptnIndex == 0, just increment strIndex -- can't jump back
 * 						 else, try to jump back: arr[substrIndex-1]
 * (We matched up until this point to get here.)
 * 
 * Time: O(n+m) (n to match, and m to build substring array)
 * Space: O(m) (substring array)
 */
public class KmpSubstringMatching {

	public static boolean contains(String str, String substr) {
		if(str == null) {
			return substr == null;
		}
		
		if(substr == null) {
			return str == null;
		}
		
		if(str.length() < substr.length()) {
			return false;
		}
		
		return KMP(str, substr);
	}

	private static boolean KMP(String str, String substr) {
		int[] substrFsa = getSubstrFsa(substr);
		
		int strIndex = 0;
		int substrIndex = 0;

		while(strIndex < str.length() && substrIndex < substr.length()) {
			if(str.charAt(strIndex) == substr.charAt(substrIndex)) {
				strIndex++;
				substrIndex++;
			} else {
				if(substrIndex == 0) {
					// Can't jump back
					strIndex++;
				} else {
					// Try jumping back
					substrIndex = substrFsa[substrIndex-1];
				}
			}
		}
		
		// Reached the end of the pattern
		return substrIndex == substr.length();
	}
	
	private static int[] getSubstrFsa(String substr) {
		
		int [] substrArr = new int[substr.length()];
		
		int index = 0;
		int i = 1;
		
		while(i < substr.length()) {
			
			if(substr.charAt(i) == substr.charAt(index)) {
				index++;
				substrArr[i] = index;
				i++;
			} else {
				if(index == 0) {
					// Can't jump back any farther
					substrArr[i] = 0;
					i++;
				} else {
					// Jump back and try again.
					// We must have matched the pattern up until this character,
					// so try to see if we match the pattern up until now.
					index = substrArr[index-1];
				}
			}
			
		}
		
		return substrArr;
	}
}
