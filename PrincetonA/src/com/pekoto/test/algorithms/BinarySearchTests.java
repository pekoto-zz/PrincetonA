package com.pekoto.test.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.pekoto.algorithms.BinarySearch;

/**
 * Unit tests for the BinarySearcher class
 */
public class BinarySearchTests {

    @Test
    void testSearch_keyFoundInLowerHalf() {
        int [] arr = {12, 46, 72, 180, 2000, 482712};
        int key = 72;

        int result = BinarySearch.search(arr, key);

        assertEquals(2, result);
    }

    @Test
    void testSearch_keyFoundInUpperHalf() {
        int [] arr = {12, 46, 72, 180, 2000, 482712};
        int key = 2000;

        int result = BinarySearch.search(arr, key);

        assertEquals(4, result);
    }

    @Test
    void testSearch_keyFoundInLowestElement() {
        int [] arr = {12, 46, 72, 180, 2000, 482712};
        int key = 12;

        int result = BinarySearch.search(arr, key);

        assertEquals(0, result);
    }

    @Test
    void testSearch_keyFoundInHighestElement() {
        int [] arr = {12, 46, 72, 180, 2000, 482712};
        int key = 482712;

        int result = BinarySearch.search(arr, key);

        assertEquals(5, result);
    }

    @Test
    void testSearch_keyNotFound() {
        int [] arr = {12, 46, 72, 180, 2000, 482712};
        int key = 22;

        int result = BinarySearch.search(arr, key);

        assertEquals(-1, result);
    }
}
