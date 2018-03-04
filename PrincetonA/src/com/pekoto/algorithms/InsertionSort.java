package com.pekoto.algorithms;

public class InsertionSort {

    public static void sort(int [] arr) {

        for(int i = 0; i < arr.length; i++) {

            int j = i;

            while(j > 0 && (arr[j] > arr[j-1])) {
                    // Swap elements while right element is bigger than left
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
            }
        }
    }
}
