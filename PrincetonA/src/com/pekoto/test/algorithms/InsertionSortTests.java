package com.pekoto.test.algorithms;

import org.junit.jupiter.api.Test;
import com.pekoto.algorithms.InsertionSort;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the InsertionSort class
 */
public class InsertionSortTests {

    @Test
    public void testSort() {
        int [] arr = {324, 21, 4, 192, 56};

        InsertionSort.sort(arr);

        assertEquals(4, arr[0]);
    }
}