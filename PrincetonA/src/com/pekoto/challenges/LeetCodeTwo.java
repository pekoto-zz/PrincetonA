package com.pekoto.challenges;

public class LeetCodeTwo {

	/*
	 * Returns the min number of swaps required to sort the array, assuming the
	 * array is of 0-n-1 elements.
	 * 
	 * Consider 0, 2, 1, 3 0 1 2 3 0 > 0 swaps 1 > swaps with 2: 0 1 2 3 2 > 0 swaps
	 * (was previously sorted) 3 > 0 swaps
	 * 
	 * Going deeper on the theory of this, we can think about cyclic swapping:
	 * 
	 * Imagine we have a list like
	 * 
	 * 2 3 1 0 5 3 0 1 2 3 4 5
	 * 
	 * We go through our indices, and at each unseen index we go to the index
	 * represented by the value at that index. Starting at 0...
	 * 
	 * 0 > 2 > 1 > 3 > 0 (we hit a cycle, so stop there and call this...) (g1) 
	 * 4 > 5 > 4 (g2)
	 * 
	 * Now each list if it contains all the numbers 0-n-1 must cycle eventually.
	 * 
	 * Now, we can also prove that these groups must be unique of each other: If a
	 * number in one group was contained in another group the two groups would have
	 * ended up being unioned together.
	 * 
	 * Corollary: It means the union of all of our groups must cover the entire set
	 * of n numbers.
	 * 
	 * Now we need to show that the number of swaps needed to resolve a group of
	 * size k is given by k-1. (resolve a group = put element in its correct
	 * position)
	 * 
	 * 0>0, 1>1, 2>2 (size 1: 0 swaps) 
	 * 0>3>0, 2>1>2 (size 2: 1 swap) 
	 * E.g.:
	 * 3 2 1 0 
	 * 0 1 2 3
	 * 
	 * So, the total number of swaps to make the graph sorted is given by summing up
	 * the min swaps for each group.
	 * 
	 * E.g., 
	 * 0 2 1 3 
	 * 0 1 2 3
	 * 
	 * 0>0 (size 1=0) 
	 * 1>2>1 (size 2 = 1) 
	 * 3>3 (size 1=0) =1
	 * 
	 */
	public int minSwapsToSort(int[] arr) {
		int swaps = 0;

		for (int index = 0; index < arr.length; index++) {

			int valueAtIndex = arr[index];

			while (index != valueAtIndex) {
				swap(arr, index, valueAtIndex);
				swaps++;
				valueAtIndex = arr[index];
			}
		}

		return swaps;
	}

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
