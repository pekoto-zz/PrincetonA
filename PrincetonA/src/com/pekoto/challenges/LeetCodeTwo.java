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
	
	/*
	 * Returns the min number of swaps required so that "couples"
	 * (i>i+1) are next to each other.
	 * 
	 * This is very similar to the problem above, but the conditions are different.
	 * Instead of breaking when the value at the index matches the index, we want to break
	 * when the partner is in the correct position.
	 * 
	 * There would be another way to do this: break the array into cyclic components
	 * using union find. Then sum of the (size of each component-1).
	 * 
	 */
	public int minSwapsCouples(int[] row) {
		
		// First we'll build up a map of what each element's
		// partner should be, and where it is in the position array
		//            0 1 2 3
		// row:      [0 2 1 3]
		//            0 1 2 3
		// partner:  [1 0 3 2]
		//            0 1 2 3
		// position: [0 2 1 3]
		
		int [] partners = new int[row.length];
		int [] positions = new int[row.length];
		
		for(int i = 0; i < row.length; i++) {
			partners[i] = i % 2 == 0 ? i+1 : i-1;
			positions[row[i]] = i;
		}
		
		int swaps = 0;
		
		for(int firstPartner=0; firstPartner < row.length; firstPartner++) {
			// Get this person's target partner
			int targetPartner = partners[row[firstPartner]];
			// Get the position of this partner
			int targetPartnerPos = positions[targetPartner];
			// Get the partner next to the target partner
			int targetPartnerPartner = partners[targetPartnerPos];
			
			// Now, while the first partner isn't sitting next to his target partner
			// let's swap him with the person who is sitting there
			while(firstPartner != targetPartnerPartner) {
				swaps++;
				swap(row, firstPartner, targetPartnerPartner);
				swap(positions, row[firstPartner], row[targetPartnerPartner]);
				
				targetPartner = partners[row[firstPartner]];
				targetPartnerPos = positions[targetPartner];
				targetPartnerPartner = partners[targetPartnerPos];
			}
			
		}
		
		return swaps;
	}
}
