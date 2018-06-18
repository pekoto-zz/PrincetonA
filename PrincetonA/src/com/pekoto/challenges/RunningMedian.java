package com.pekoto.challenges;

import java.util.PriorityQueue;
import java.util.Stack;

// The idea is to have 2 heaps, one of which holds values <= median
// and the other with values > median.
// The key invariant is that the <= heap must always be >= size of the >= heap.
//
// Adding elements:
//  1. If heaps same sizes...
//  2. If new element > min element in >= heap...
//      2.1 Add the smallest element in greater heap to below heap (to keep size invariant)
//      2.2 Add new element to greater heap
//  3. Else, add to less than heap
//  4. If heaps different sizes, if element < max of less than heap
//      4.1 Add largest element in less than heap to greater heap
//      4.2 Add element to less than heap
//  5. Else, add to great than heap
//
//  Getting median:
//  1. If heaps are same size, get bottom of greater heap and top of less than heap, /2
//  2. Else, just get the top of the less than heap
//
// Get median: O(1)
// Update: O(log n)
public class RunningMedian {

    // This will hold values below or equal to the median
    Stack<Integer> belowOrEqualToMedian;
    
    // This will hold values above the median
    PriorityQueue<Integer> aboveMedian;
    
    // So the top of below... and the bottom of above... will return the median (if even number of elements)
    // Or the top of below... will hold the median (if odd number of elements)
    
    public RunningMedian() {
        aboveMedian = new PriorityQueue<Integer>();
        belowOrEqualToMedian = new Stack<Integer>();
    }
    
    public void add(int n) {
        // maxHeap.size() must always be >= minHeap.size()
        if(belowOrEqualToMedian.size() == aboveMedian.size()) {
            if((!aboveMedian.isEmpty()) && (n > aboveMedian.peek())) {
                
                // If the number is greater than the top of minHeap,
                // add the top of minHeap to maxHeap, then add to minHeap
                belowOrEqualToMedian.push(aboveMedian.poll());
                aboveMedian.add(n);
            } else {
                // Number is less than top of minHeap
                belowOrEqualToMedian.push(n);
            }
        } else {
            // Heaps are different sizes
            if(n < belowOrEqualToMedian.peek()) {
                // Add top of maxHeap to minHeap
                aboveMedian.add(belowOrEqualToMedian.pop());
                belowOrEqualToMedian.push(n);
            } else {
                aboveMedian.add(n);
            }
        }
    }
    
    public double getMedian() {
        // maxHeap is always >= as minHeap. If maxHeap is empty, we have no media.
        if(belowOrEqualToMedian.isEmpty()) {
            return 0;
        }
        
        if(belowOrEqualToMedian.size() == aboveMedian.size()) {
            // We have an even number of numbers.
            // Median is middle 2 nums/2
            return ((double) aboveMedian.peek() + (double) belowOrEqualToMedian.peek()) / 2;
        } else {
            // Heaps are different sizes, maxHeap must have the media
            return belowOrEqualToMedian.peek();
        }
    }
}
