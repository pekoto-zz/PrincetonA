package com.pekoto.algorithms;

/**
 * Given a list of items with weight and value: 
 * Item:   0 1 2 3 4 
 * Weight: 2 4 1 4 7 
 * Value:  4 2 1 5 1
 * 
 * And a knapsack of limited capacity, what is the maximum value you can carry
 * in the knapsack?
 * 
 * Example: Capacity=8 gives max value=10 (items 0, 2, and 3)
 * 
 * 1. Set up a matrix of capacity x items (values + weight)
 * 2. Initialise the first row/col to 0 (we have 0 capacity or 0 item/value)
 * 3. If the capacity (column) is less than the weight of the item, then the max
 *    value we can carry is the value from the row above (i.e., the value as if
 *    we didn't take this item at all).
 * 4. If we do have capacity to carry this item, the max value is the max of either:
 *  4.1 The value as if we didn't carry this item at all (the value from the row above)
 *  4.2 The value of this item + the value of what we can carry using left over capacity
 *      (left over capacity = row above, capacity-weight of this item)
 * 5. Final max value will be in the lower right of the matrix
 * 
 */
public class Knapsack {

    public static int getMaxValue(int weights[], int values[], int maxCapacity) {
        
        int maxValues[][] = new int[values.length + 1][maxCapacity + 1];
        
        // Thinking of this as a matrix, the itemIndex is the row and the capacity is the column
        for (int itemIndex = 0; itemIndex <= values.length; itemIndex++) {
            for (int capacity = 0; capacity <= maxCapacity; capacity++) {
                if (itemIndex == 0 || capacity == 0) {
                    // If the capacity is 0 or we're looking at 0 items, set the index to 0.
                    maxValues[itemIndex][capacity] = 0;
                } else if (capacity < weights[itemIndex-1]) {
                    // If the capacity is < the weight of this item, the max weight we can store is
                    // the weight if we didn't take this item (i.e. the weight in the row above)
                    maxValues[itemIndex][capacity] = maxValues[itemIndex-1][capacity];
                } else {
                    // If we do have capacity for this item, we must choose the max value between:
                    // 1. The value taking this item + value we can carry using left over capacity
                    //    (left over capacity value = row above, shifted left by capacity-weight of this item)
                    // 2. The value of not taking this item at all (the value in the row above)
                    
                    int valueOfThisItem = values[itemIndex-1];  // We are 1-indexing in the loop
                    int spareCapacityIndex = capacity-weights[itemIndex-1];
                    int valueOfSpareCapacity=maxValues[itemIndex-1][spareCapacityIndex];
                    
                    int valueTakingThisItem = valueOfThisItem + valueOfSpareCapacity;
                    int valueWithoutTakingThisItem = maxValues[itemIndex-1][capacity];
                    maxValues[itemIndex][capacity] = Math.max(valueTakingThisItem, valueWithoutTakingThisItem);
                }
            }
        }

        return maxValues[values.length][maxCapacity];
    }
}
