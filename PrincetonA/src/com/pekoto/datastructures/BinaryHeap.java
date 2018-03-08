package com.pekoto.datastructures;

public class BinaryHeap<T extends Comparable<T>> {

    private static final int DEFAULT_SIZE = 11;
    private T[] arr;
    
    // Set the root at one to make the math easier
    private int nextElementIndex = 1;
    
    public BinaryHeap() {
        arr = (T[]) new Comparable[DEFAULT_SIZE];
    }
    
    public BinaryHeap(int initialSize) {
        arr = (T[]) new Comparable[initialSize+1];
    }
    
    public void put(T value) {
        if(nextElementIndex == arr.length) {
            resize(arr.length*2);
        }
        
        arr[nextElementIndex] = value;
        swim(nextElementIndex);
        nextElementIndex++;
    }
    
    private void swim(int childIndex) {
        int parentIndex = childIndex/2;
        
        while(childIndex > 0 && lessThan(parentIndex, childIndex)) {
            swap(parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex /= 2;
        }
    }
    
    public T removeMax() {
        nextElementIndex--;
        swap(1, nextElementIndex);
        T value = arr[nextElementIndex];
        arr[nextElementIndex] = null;
        
        sink(1);
        
        if(nextElementIndex == arr.length/4) {
            resize(arr.length/2);
        }
        
        return value;
    }
    
    private void sink(int parentIndex) {
        int childOneIndex = parentIndex*2;
        int childTwoIndex = childOneIndex+1;
        
        while(parentIndex < nextElementIndex-1 && (lessThan(parentIndex, childOneIndex) || lessThan(parentIndex, childTwoIndex))) {
            int maxChildIndex = max(childOneIndex, childTwoIndex);
            swap(parentIndex, maxChildIndex);
            parentIndex = maxChildIndex;
            childOneIndex = parentIndex*2;
            childTwoIndex = childOneIndex+1;
        }
    }
    
    private void swap(int indexOne, int indexTwo) {
        T temp = arr[indexOne];
        arr[indexOne] = arr[indexTwo];
        arr[indexTwo] = temp;
    }
    
    private boolean lessThan(int indexOne, int indexTwo) {
        if(arr[indexTwo] == null) {
            return false;
        } else if (arr[indexOne] == null) {
            return false;
        } else {
            return arr[indexOne].compareTo(arr[indexTwo]) < 0;
        }
    }
    
    private int max(int indexOne, int indexTwo) {
        if(lessThan(indexOne, indexTwo)) {
            return indexTwo;
        } else {
            return indexOne;
        }
    }
    
    public int size() {
        return nextElementIndex -1;
    }
    
    public boolean isEmpty() {
        return nextElementIndex == 1;
    }
    
    private void resize(int newSize) {
        T [] copy = (T[]) new Comparable[newSize];
        
        for(int i = 0; i < nextElementIndex; i++) {
            copy[i] = arr[i];
        }
        
        arr = copy;
    }
}
