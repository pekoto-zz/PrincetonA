package com.pekoto.challenges;

/*
 * An immutable bit array. Can effectively be used to
 * store 32 * size number of integers in a set like representation.
 */
public class BitArray {

    private int [] arr;
    private final int INT_SIZE = 32;
    
    public BitArray(int size) {
        arr = new int[size];
    }
    
    public void put(int n) {
        int index = n/INT_SIZE;
        int bitToSet = n%INT_SIZE;
    
        setIthBit(index, bitToSet);
    }
    
    public boolean contains(int n) {
        int index = n/INT_SIZE;
        int bitToGet = n%INT_SIZE;
        
        return getIthBit(index, bitToGet);
    }
    
    private boolean getIthBit(int index, int i) {
        int num = arr[index];
        return (num & (1 << i)) == 1;
    }
    
    private void setIthBit(int index, int i) {
        int num = arr[index];
        arr[index] = num | (1 << i);
    }
}
