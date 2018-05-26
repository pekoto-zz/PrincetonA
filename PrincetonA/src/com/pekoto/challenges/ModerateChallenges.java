package com.pekoto.challenges;

public class ModerateChallenges {

    /*
     * Swaps two numbers in place using XORs.
     *
     * Example:
     * a = 4; // 0100
     * b = 2; // 0010
     *
     * a = a^b; // 0110
     * b = a^b; // 0100 (4)
     * a = a^b; // 0010 (2)
     */
    public static SimpleTuple swapInPlace(int a, int b) {
        a = a^b;
        b = a^b;
        a = a^b;
        
        return new SimpleTuple(a, b);
    }
}
