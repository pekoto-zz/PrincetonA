package com.pekoto.algorithms;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

/*
 * Searches for a substring in a main string.
 * 
 * Essentially like computing a moving average in a stream:
 * 1. Compute a hash for the pattern
 * 2. Take the hash in the main string
 * 3. If it matches, either check if the strings match, or just return that index
 *    (if just return the index, there is the potential the result could be wrong --
 *     in the case of hash collisions)
 * 4. If the hash doesn't match, subtract the first letter's value from the hash,
 *    and add the next letter's value
 * 
 * Time: O(nm) (worst case, but typical O(n+m) assuming no hash collisions)
 * Space: O(m)
 * 
 * To check for multiple substrings/patterns, simply put all of the hashes in a set,
 * and then check if the string has is in that set.
 * 
 */
public class RabinKarpSubstringMatching {

	private long patternHash;
	private int patternLen;
	private long primeModulus;	// long random price
	private int radix;
	private long radP; // radix^(patterLen-1) % modulus
	
	public RabinKarpSubstringMatching(String pattern) {
		patternLen = pattern.length();
		radix = 256;	// Extended ASCII
		primeModulus = 103680;	// Random prime
		
		radP = 1;
		
		for(int i = 1; i <= patternLen-1; i++) {
			radP = (radix * radP) % primeModulus;
		}
		
		patternHash = hash(pattern, patternLen);
	}
	
	public int search(String text) {
		
		// calculate the initial hash
		long textHash = hash(text, patternLen);
		
		if(patternHash == textHash) {
			return 0;
		}
		
		for(int i = patternLen; i < text.length(); i++) {
			
			textHash = (textHash + primeModulus - radP*text.charAt(i-patternLen) % primeModulus) % primeModulus;
			textHash = (textHash*radix + text.charAt(i)) % primeModulus;
			
			if(patternHash == textHash) {
				// If we want to be SURE the patterns
				// match we must check substrings here
				return i - patternLen + 1;
			}
			
		}
		
		return -1;
	}
	
	// See Horner's method
	private long hash(String key, int M) {
		long h = 0;
		
		for(int j = 0; j < M; j++) {
			h = (radix * h + key.charAt(j)) % primeModulus;
		}
		
		return h;
	}
}
