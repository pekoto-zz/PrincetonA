package com.pekoto.challenges;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> lowerHalf;
    private PriorityQueue<Integer> upperHalf;
    
    public MedianFinder() {
        lowerHalf = new PriorityQueue<Integer>(new MaxComparator());
        upperHalf = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if(lowerHalf.isEmpty()) {
            lowerHalf.add(num);
        } else if (num < lowerHalf.peek()) {
            upperHalf.add(lowerHalf.poll());
            lowerHalf.add(num);
        } else {
            upperHalf.add(num);
        }
        
        if(upperHalf.size() > lowerHalf.size()) {
            lowerHalf.add(upperHalf.poll());
        }
    }
    
    public double findMedian() {
        if(lowerHalf.isEmpty() && upperHalf.isEmpty()) {
            return 0;
        } else if (upperHalf.isEmpty()  || (lowerHalf.size() > upperHalf.size())) {
            return lowerHalf.peek();
        } else {
            return (double) (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        }
    }
    
    private class MaxComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer arg0, Integer arg1) {
            return arg1-arg0;
        }
        
    }
}
