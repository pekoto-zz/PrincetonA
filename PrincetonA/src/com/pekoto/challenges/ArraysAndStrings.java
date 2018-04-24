package com.pekoto.challenges;

public class ArraysAndStrings {
    
    /**
     * Returns true if all characters in a string are unique.
     * Assumes only extended ASCII chars.
     * 
     * Can run without using the array by sorting the string
     * and then comparing if two contiguous elements are the same.
     * 
     * Performance: O(n)
     * Space: O(1)
     * 
     * @param str Checks a string for all unique characters
     * @return True if all chars are unique
     */
    public static boolean areAllCharsUnique(String str) {
        
        final int EXTENDED_ASCII_LENGTH = 256;
        
        if(str.length() > EXTENDED_ASCII_LENGTH) {
            return false;
        }
        
        boolean [] charFound = new boolean[EXTENDED_ASCII_LENGTH];
        
        for(int i = 0; i < str.length(); i++) {
            if(charFound[str.charAt(i)]) {
                return false;
            } else {
                charFound[str.charAt(i)] = true;
            }
        }
        
        return true;   
    }
    
    /**
     * Returns true if two strings are permutations of each other.
     * Assumes strings are not null and use extended ASCII characters.
     * 
     * Could also sort the strings and compare each element, if you
     * wanted to avoid using array completely.
     * 
     * Performance: O(n)
     * Space: O(1)
     * 
     * @param strOne
     * @param strTwo
     * @return true if strings are permutations of each other, false otherwise
     */
    public static boolean arePermutations(String strOne, String strTwo) {
        
        if(strOne.length() != strTwo.length()) {
            return false;
        }
        
        final int EXTENDED_ASCII_LENGTH = 256;
        
        int [] charCounts = new int[EXTENDED_ASCII_LENGTH];
        
        for(int i = 0; i < strOne.length(); i++) {
            charCounts[strOne.charAt(i)]++;
        }
        
        for(int i = 0; i < strTwo.length(); i++) {
            charCounts[strTwo.charAt(i)]--;
            
            if(charCounts[strTwo.charAt(i)] < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Returns true if two strings are only 1 edit away.
     * 
     * Performance: O(n) (where n is the length of the shorter string)
     * 
     * @param strOne
     * @param strTwo
     * @return True if the strings are 1 edit away, false otherwise
     */
    public static boolean oneAway(String strOne, String strTwo) {
        
        if(strOne.length() == strTwo.length()) {
            return oneEditAway(strOne, strTwo);
        } else if (strOne.length()-1 == strTwo.length()) {
            return oneInsertAway(strTwo, strOne);
        } else if (strTwo.length()-1 == strOne.length()) {
            return oneInsertAway(strOne, strTwo);
        } else {
            return false;
        }
    }
    
    private static boolean oneEditAway(String strOne, String strTwo) {
        boolean editMade = false;
        
        for(int i=0; i < strOne.length(); i++) {
            if(strOne.charAt(i) != strTwo.charAt(i)) {
                if(editMade) {
                    return false;
                } else {
                    editMade = true;
                }
            }
        }
        
        return true;
    }
    
    private static boolean oneInsertAway(String shorter, String longer) {
        boolean insertMade = false;
        
        for(int i=0, j=0; i < shorter.length(); i++, j++) {
            if(shorter.charAt(i) != longer.charAt(j)) {
                if(insertMade) {
                    return false;
                } else {
                    insertMade = true;
                    i--;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Zeros out the entire row and column in an NxM matrix
     * if an element in that row or column is 0.
     * 
     * There is an easier way to do this in O(n) space --
     * just keep track of which rows and columns have 0 in them
     * using boolean arrays.
     * 
     * The gotcha here is that you must do a read ahead
     * to work out where the 0s are, otherwise you will end
     * up zeroing out the whole matrix.
     * 
     * Performance: O(nm)
     * Space: O(1)
     * 
     * @param matrix
     */
    public static void zeroOut(int [][] matrix) {
		boolean firstColHasZero = false;
		boolean firstRowHasZero = false;
		
		// Check if the first row has a 0
		// If it has a zero, zero out the whole row
		for(int col=0; col < matrix[0].length; col++) {
			if(matrix[0][col] == 0) {
				firstRowHasZero = true;
				break;
			}
		}
		
		// Check if the first col has a 0
		// If it has a zero, zero out the whole column
		for(int row=0; row < matrix.length; row++) {
			if(matrix[row][0] == 0) {
				firstColHasZero = true;
				break;
			}
		}
		
		// Go through the rest of the matrix, using the first row
		// and col to mark if a zero was found
		for(int row=1; row < matrix.length; row++) {
			for(int col=1; col < matrix[0].length; col++) {
				if(matrix[row][col] == 0) {
					matrix[row][0] = 0;
					matrix[0][col] = 0;
				}
			}
		}
		
		// Zero out the rows and cols using the first row and col
		for(int col=0; col < matrix[0].length; col++) {
			if(matrix[0][col] == 0) {
				zeroOutCol(matrix, col);
			}
		}
		
		for(int row=0; row < matrix.length; row++) {
			if(matrix[row][0] == 0) {
				zeroOutRow(matrix, row);
			}
		}
		
		if(firstColHasZero) {
			zeroOutCol(matrix, 0);
		}
		
		if(firstRowHasZero) {
			zeroOutRow(matrix, 0);
		}
	}
	
	private static void zeroOutRow(int [][] matrix, int row) {
		for(int col = 0; col < matrix[0].length; col++) {
			matrix[row][col] = 0;
		}
	}
	
	private static void zeroOutCol(int [][] matrix, int col) {
		for(int row = 0; row < matrix.length; row++) {
			matrix[row][col] = 0;
		}
	}
	
	/**
	 * Returns true if string two is a rotation of string two,
	 * or false otherwise.
	 * 
	 * In this we can call isSubstring (contains) once.
	 * We could also create a version that used O(1) space using pointers.
	 * 
	 * Performance: O(n) (assuming contains runs in O(n+m time -- we have to go through each char in both strings)
	 * Space: O(n) (we create a new string when we duplicate string one)
	 * 
	 * @param strOne
	 * @param strTwo
	 * @return True if string two is a rotation of string two
	 */
	public static boolean isRotation(String strOne, String strTwo) {
	    if(strOne.length() != strTwo.length()) {
	        return false;
	    }
	    
	    if(strOne.length() == 0) {
	        return false;
	    }
	    
	    return (strOne+strOne).contains(strTwo);
	}
}
