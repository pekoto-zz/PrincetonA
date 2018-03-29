package com.pekoto.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Memoization is a dynamic programming technique.
 * Store items in a HashMap to avoid duplicate recursive calls.
 * 
 * Fibonacci(n) = Fibonacci(n-1) + Fibonacci(n-2)
 * I.e., 1 1 2 3 5 8 13 21 34 55...
 */
public class FibonacciMemoization {

    public Map<Integer, Integer> memoMap = new HashMap<Integer, Integer>();
    
    public int getFib(int n) {
        
        if(n < 0) {
            throw new IllegalArgumentException("n cannot be less than 0");
        }
        
        if(n <= 1) {
            return n;
        }
        
        if(memoMap.containsKey(n)) {
            return memoMap.get(n);
        }
     
        // Number not yet calculated
        // Calculate it by recursion and store the result
        int result = getFib(n-1) + getFib(n-2);
        memoMap.put(n, result);
        
        return result;
    }
}
