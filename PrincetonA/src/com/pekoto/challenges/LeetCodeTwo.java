package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	
	/*
	 * Returns all permutations of a string containing
	 * optional wildcards.
	 * 
	 * E.g.:
	 * abc{d,e}f{gh,ij}
	 * [abcdfgh, abcdfij, abcefgh, abcefij]
	 */
	public List<String> bashPermutations(String s) {
		
		List<String> results = new ArrayList<>();
		
		if(s == null || s.length() == 0) {
			return results;
		}
		
		getPermutations("", s, results);
		
		return results;
	}
	
	private void getPermutations(String prefix, String suffix, List<String> results) {
		
		if(suffix.length() == 0) {
			results.add(prefix);
			return;
		}
		
		StringBuffer sb = new StringBuffer(prefix);
		
		for(int i = 0; i < suffix.length(); i++) {
			if(suffix.charAt(i) != '{') {
				sb.append(suffix.charAt(i));
			} else {
				int end = suffix.indexOf('}');
				String[] parts = suffix.substring(i+1, end).split(",");
				
				for(String part : parts) {
					getPermutations(sb.toString()+part, suffix.substring(end+1), results);
				}
				
				// The rest of the string will be handled by recursive calls
				break;
			}
		}
	}
	
	/*
	 * Merge two sorted implementations of the list interface.
	 * Time: O(n+m)
	 * Space: O(n+m) for merged new list size
	 * 
	 * Point: We can't use the normal mergesort process
	 * 		  because a linked list would take O(n) time to
	 * 		  get the element at index n.
	 */
	public ArrayList<Integer> mergeSortedLists(List<Integer> listOne, List<Integer> listTwo) {
		ArrayList<Integer> mergedList = new ArrayList<>();
		
		if(listOne == null && listTwo == null) {
			throw new NullPointerException("Lists cannot be null");
		}
		
		if(listOne == null || listOne.size() == 0) {
			return new ArrayList<Integer>(listTwo);
		}
		
		if(listTwo == null || listTwo.size() == 0) {
			return new ArrayList<Integer>(listOne);
		}
		
		Iterator<Integer> listOneIter = listOne.iterator();
		Iterator<Integer> listTwoIter = listTwo.iterator();
		
		Integer listOneVal = listOneIter.next();
		Integer listTwoVal = listTwoIter.next();
		
		while(listOneVal != null && listTwoVal != null) {
			if(listOneVal < listTwoVal) {
				mergedList.add(listOneVal);
				listOneVal = nextOrNull(listOneIter);
			} else {
				mergedList.add(listTwoVal);
				listTwoVal = nextOrNull(listTwoIter);
			}
		}
		
		while(listOneVal != null) {
			mergedList.add(listOneVal);
			listOneVal = nextOrNull(listOneIter);
		}
		
		while(listTwoVal != null) {
			mergedList.add(listTwoVal);
			listTwoVal = nextOrNull(listTwoIter);
		}
		
		return mergedList;
	}
	
	private Integer nextOrNull(Iterator<Integer> iter) {
		if(iter.hasNext()) {
			return iter.next();
		} else {
			return null;
		}
	}
	
	/*
	 * Given a grid and points on that grid, find the point on the grid such
	 * that the sum of the Manhattan distances from that point to the other points
	 * is minimized.
	 * 
	 * (Manhattan distance = distance between 2 points on a grid, given by |x1-x2|+|y1-y2|)
	 * 
	 * 0,0------0,1------1,1
	 *  |        |        |
	 * 1,0------1,1------1,2
	 *  |        |        |
	 * 2,0------2,1------2,2
	 * 
	 * Imagine we had points at 0,0 , 0,2 and 2,2, we can get the Manhattan distance sum for each
	 * coordinate:
	 * 
	 * 0,0 = 0 + 2 + 4 = 6
	 * 0,1 = 1 + 1 + 3 = 5
	 * 0,2 = 0 + 2 + 2 = 4
	 * 1,0 = 1 + 3 + 3 = 7
	 * 1,1 = 2 + 2 + 2 = 6
	 * 1,2 = 3 + 1 + 1 = 5
	 * 2,0 = 2 + 4 + 2 = 8
	 * 2,1 = 3 + 3 + 1 = 7
	 * 2,2 = 4 + 2 + 0 = 6
	 * 
	 * So the best point is 0,2.
	 * 
	 * To get the optimum solution we can use a tedious trick that uses the property of Manhattan distance:
	 * namely, x and y can be separated.
	 * So simply get the median of the x and y coordinates.
	 * 
	 * This gives us O(n log n) time, where n is the number of points.
	 * (Need to *prove* this works for even n)
	 * (Also, we don't even need the grid, assuming the points are valid)
	 */
	public int[] minManhattanDistance(int[][] grid, List<int[]> points) {
		
		if(grid[0].length <= 1) {
			return new int[] {0, 0};
		}
		
		List<Integer> xCoords = new ArrayList<>();
		List<Integer> yCoords = new ArrayList<>();
		
		for(int[] point : points) {
			xCoords.add(point[1]);
			yCoords.add(point[0]);
		}
		
		Collections.sort(xCoords);
		Collections.sort(yCoords);
		
		int[] result = new int[2];
		int mid = xCoords.size() / 2;
		
		if(xCoords.size() % 2 == 0) {
			result[0] = (yCoords.get(mid) + yCoords.get(mid+1)) / 2;	
			result[1] = (xCoords.get(mid) + xCoords.get(mid+1)) / 2;
		} else {
			result[0] = yCoords.get(mid);
			result[1] = xCoords.get(mid);
		}
		
		return result;
	}
	
	/*
	 * Gets all permutations of an array, where the array contains duplicates.
	 * Normally we swap the index on each iteration.
	 * However, note: if we swap two indices with the same value,
	 * we would just end up with a duplicate permutation.
	 * (Except in the case where i == index, which happens when generating
	 * the permutation containing the initial ordering).
	 * 
	 * Formula for permutations with repeats:
	 * n!/(repeat element 1 count)!*(repeat element 2 count)!...
	 * 
	 * E.g., for {1, 1, 2, 3}
	 * We have 4 elements, and 1 is repeated twice, so:
	 * 4!/2! = 24/2 = 12 unique permutations
	 */
	public List<int[]> getDuplicatePermutations(int [] arr) {
		List<int[]> permutations = new ArrayList<>();
		
		getDuplicatePermutations(arr, 0, permutations);
		
		return permutations;
	}
	
	private void getDuplicatePermutations(int [] arr, int index, List<int[]> permutations) {
		if(index == arr.length) {
			permutations.add(arr.clone());
			return;
		}
		
		for(int i = index; i < arr.length; i++) {
			if(index != i && arr[index] == arr[i]) {
				continue;
			}
			
			swap(arr, index, i);
			getDuplicatePermutations(arr, index+1, permutations);
			swap(arr, index, i);
		}
	}
	

	
	/*
	 * Given a number of hours, and a list of integers
	 * representing the number of hours a meeting takes,
	 * fill as many hours as you can, AND return the list of those hours.
	 * 
	 * This is basically like the knapsack problem, but you have to backtrack
	 * through the generated matrix to get the hours taken.
	 */
	public List<Integer> getAsManyMeetings(int[] meetings, int hours) {
		List<Integer> results = new ArrayList<>();
		
		if(meetings.length == 0 || hours == 0) {
			return results;
		}
		
		// First use DP to get the max hours taken
		// The cols represent hours, and rows represent meeting hours
		int[][] max = new int[meetings.length+1][hours+1];
		
		for(int meetingIndex = 1; meetingIndex <= meetings.length; meetingIndex++) {
			for(int hour = 1; hour <= hours; hour++) {
				int hoursForThisMeeting = meetings[meetingIndex-1];
				
				if(hoursForThisMeeting > hour) {
					// If this meeting doesn't fit into this hour, just take the value from the row above
					max[meetingIndex][hour] = max[meetingIndex-1][hour];
				} else {
					// Otherwise choose between time of taking this meeting, or not taking this meeting
					int hoursWithThisMeetingAndPreviousMax = hoursForThisMeeting + max[meetingIndex-1][hour-(meetingIndex-1)];
					int hoursWithoutThisMeeting = max[meetingIndex-1][hour];
					
					if(hoursWithThisMeetingAndPreviousMax > hour) {
						// If taking this meeting and the previous max > current hours,
						// then just choose between taking the previous max or this meeting
						max[meetingIndex][hour] = Math.max(hoursForThisMeeting, hoursWithoutThisMeeting);
					} else {
						// Else choose between taking this meeting and previous max, or just previous max
						max[meetingIndex][hour] = Math.max(hoursWithThisMeetingAndPreviousMax, hoursWithoutThisMeeting);						
					}
				}
			}
		}
		
		// Now we need to backtrack through matrix to get the hours that were taken:
		// 1. Start in the lower right
		// 2. If this value is the same as the row above, it came from the row above, move up
		// 3. Else move left by this value
		int maxHours = max[max.length-1][max[0].length-1];
		int hourIndex = max[0].length-1;
		
		for(int meetingIndex = max.length-1; meetingIndex > 0; meetingIndex--) {
			if(maxHours == max[meetingIndex-1][hourIndex]) {
				// Move up a row -- result came from above
				continue;
			} else {
				// Add this to results and move left by hours taken
				results.add(meetings[meetingIndex-1]);
				maxHours -= meetings[meetingIndex-1];
				hourIndex -= meetings[meetingIndex-1];
			}
		}
		
		return results;
	}
}
