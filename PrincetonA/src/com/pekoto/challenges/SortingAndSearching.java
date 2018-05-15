package com.pekoto.challenges;

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
}
