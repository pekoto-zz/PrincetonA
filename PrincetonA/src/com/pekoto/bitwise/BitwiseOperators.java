package com.pekoto.bitwise;

/**
 * Demonstrates basic bitwise operators in Java
 */
public class BitwiseOperators {

    /**
     * Bitwise ANDs two ints.
     * Looks at both ints' binary digits, and sets a 1 iff both have a 1 in the same position.
     * 
     * Example:
     * 5 & 6 = 4
     * 
     *  5 = 0101
     *  6 = 0110
     * =4 = 0100
     * 
     * @param a The first int
     * @param b The second int
     * @return a & b (bitwise)
     */
    public int and(int a, int b) {
        return a & b;
    }
    
    /**
     * Bitwise ORs two ints.
     * Looks at both ints' binary digits, and sets a 1 iff either have 1.
     * 
     * Example:
     * 5 | 6 = 7
     * 
     *  5 = 0101
     *  6 = 0110
     * =7 = 0111
     * 
     * @param a The first int
     * @param b The second int
     * @return a | b (bitwise)
     */
    public int or(int a, int b) {
        return a | b;
    }
    
    /**
     * Bitwise XORs two ints.
     * Looks at both ints' binary digits, and sets a 1 iff only one of them has 1.
     * 
     * Example:
     * 5 ^ 6 = 3
     * 
     *  5 = 0101
     *  6 = 0110
     * =7 = 0011
     * 
     * @param a The first int
     * @param b The second int
     * @return a | b (bitwise)
     */
    public int xor(int a, int b) {
        return a ^ b;
    }
    
    /**
     * Bitwise NOTs an int.
     * Inverts every bit, so 0s become 1s and 1s become 0s.
     * This can result in -ves due to two's complement (leftmost digit is -ve).
     * 
     * Example:
     * ~0 = -1
     * 
     *  0 = 0
     * ~1 = 1
     * 
     * ~1 = -2
     * 
     *  1 = 01
     * ~1 = 10
     * 
     * ~5 = -6
     *  5 = 0101
     * -6 = 1010 (-8 + 2)
     * 
     * @param a The int to invert
     * @return ~a (bitwise)
     */
    public int not(int a) {
        return ~a;
    }
    
    /**
     * Shifts a's bits left by a given amount.
     * 
     * Shifting left once will multiply by 2,
     * shifting left twice will multiply by 4, etc.
     * 
     * Example:
     * 4 << 1 = 8
     * 
     * 0100 = 4
     * 1000 = 8
     * 
     * @param a The int whose bits to shift
     * @param amount The amount to shift a left by
     * @return a's bits shifted left by amount
     */
    public int shiftLeft(int a, int amount) {
        return a << amount;
    }
    
    /**
     * Shifts a's bits right by a given amount.
     * 
     * Shifting right once will divide by 2, etc.
     * 
     * Example:
     * 4 >> 1 = 2
     * 
     * 0100 = 4
     * 0010 = 2
     * 
     * @param a The int whose bits to shift
     * @param amount The amount to shift a right by
     * @return a's bits shifted right by amount
     */
    public int shiftRight(int a, int amount) {
        return a >> amount;
    }
}
