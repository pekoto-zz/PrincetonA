package com.pekoto.test.algorithms;

import org.junit.Test;

import com.pekoto.algorithms.Shuffle;

public class ShuffleTests {

    @Test
    public void testShuffle() {
        int [] arr = {-1, 0, 1, 2, 3, 4, 5};
        
        Shuffle.shuffle(arr);
        
        for(int i: arr) {
            System.out.print(String.format("%d ", i));
        }
        
        System.out.println();
    }
}
