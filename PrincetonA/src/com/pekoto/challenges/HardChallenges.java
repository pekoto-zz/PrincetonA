package com.pekoto.challenges;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class HardChallenges {
    
    /*
     * Adds two numbers without the plus operator.
     * 
     * Point: Normally when we add two numbers, we add the digits and the carry.
     *        We can split these steps up, adding the digits ignoring the carry,
     *        and then adding the digits taking only the carry.
     *        Then we add these two together using the same process until
     *        there is nothing left to carry.
     *        
     *  759                759            323
     * +674               +674    -->    1110
     *  323 (no carry)    1110 (carry)   1433 (nothing left to carry, so we're done)
     *  
     * In binary we can replicate this process:
     * 1. Add step: We have a 0 when both bits are 0 or 1 (XOR) (Both being 1 would result in a carry)
     * 2. Carry step: We have a 1 if i-1 of both a and b are 1 (Take bits where both are 1, then carry to left)
     */
    public static int addWithoutPlus(int a, int b) {
        while(b != 0) {
            int sum = a ^ b;    // Add without carry
            int carry = (a & b) << 1;     // Carry without add
            a = sum;
            b = carry;
        }
        
        return a;
    }
    
    /*
     * Returns a random set of m integers from an array
     * Essentially the same as Knuth's shuffle algorithm.
     */
    public static int [] getRandomSet(int [] arr, int m) {
        int [] randomSet = new int[m];
        
        for(int i = 0; i < m; i++) {
            randomSet[i] = arr[i];
        }
        
        Random rand = new Random();
        
        for(int i = m; i < arr.length; i++) {
            int randomIndex = rand.nextInt(i+1);
            
            if(randomIndex < m) {
                randomSet[randomIndex] = arr[i];
            }
        }
        
        return randomSet;
    }
    
    /*
     * Gets all subarrays. This is O(n^2), so rarely optimal,
     * but it can be a useful brute force solution if you can't
     * think of any other solution.
     * 
     * It starts checking from the longest subarray, which is generally
     * what is asked for.
     * 
     * Note: This assumes you want at least 2 elements in the subarray
     */
    public static List<int[]> getAllSubarrays(int [] arr) {
        
        List<int[]> subarrays = new ArrayList<int[]>();
        
        for(int subarrayLength = arr.length-1; subarrayLength > 0; subarrayLength--) {
            
            // I.e., for a length 5 array, will be 0 on first iteration (1 length 5 array)
            // then 1 for second iteration (2 length 4 arrays), etc.
            int startOffsetCount = arr.length-1-subarrayLength;
            
            for(int start = 0; start <= startOffsetCount; start++) {
                // Do some checking in this subarray
                // E.g., equal number of letters and numbers
                subarrays.add(getSubArray(arr, start, start + subarrayLength));
            }
        }
        
        return subarrays;
    }
    
    private static int[] getSubArray(int [] arr, int start, int end) {
        int [] subarray = new int[end-start+1];
        
        for(int i = start; i <= end; i++) {
            int subarrayIndex = i-start;
            subarray[subarrayIndex] = arr[i];
        }
        
        return subarray;
    }
    
    /* 
     * Counts the number of 2s in the range 0...n
     */
    public static int getNumOf2s(int n) {
        int count = 0;
        
        int len = String.valueOf(n).length();
        
        for(int digit = 0; digit < len; digit++) {
            count += count2sInRangeAtDigit(n, digit);
        }
        
        return count;
    }
    
    private static int count2sInRangeAtDigit(int n, int digit) {
        int powerOf10 = (int) Math.pow(10, digit);
        int nextPowerOf10 = powerOf10 * 10;
        int right = n % powerOf10;
        
        int roundDown = n - n % nextPowerOf10;
        int roundUp = roundDown + nextPowerOf10;
        
        int d = (n / powerOf10) % 10;
    
        if(d < 2) {
            return roundDown / 10;
        } else if (d == 2) {
            return roundDown / 10 + right + 1;
        } else {
            return roundUp / 10;
        }
    }
    
    /*
     * Gets the list of name frequencies, grouped by name synonym
     * 
     * 1. Construct a graph where each node contains a frequency count
     * 2. Connected the graph nodes based on synonym
     * 3. Perform a DFS on each connected component to get the true count
     * 
     * Performance: O(N+P), where N = number of names and P = pairs of synonyms
     * 
     */
    public static HashMap<String, Integer> getTrueFrequencies(HashMap<String, Integer> nameFrequencies, String[][] synonyms) {
        NameGraph graph = constructGraph(nameFrequencies);
        connectEdges(graph, synonyms);
        HashMap<String, Integer> trueFrequencies = getTrueFrequencies(graph);
        
        return trueFrequencies;
    }
    
    // Create graph nodes keys on name, with value of frequency
    private static NameGraph constructGraph(HashMap<String, Integer> nameFrequencies) {
        NameGraph graph = new NameGraph();
        
        for(Entry<String, Integer> entry: nameFrequencies.entrySet()) {
            graph.addNode(entry.getKey(), entry.getValue());
        }
        
        return graph;
    }
    
    // Connect edges based on synonym list
    private static void connectEdges(NameGraph graph, String[][] synonyms) {
        for(String [] entry : synonyms) {
            graph.addEdge(entry[0], entry[1]);
        }
    }
    
    // Do a DFS of each connected component
    private static HashMap<String, Integer> getTrueFrequencies(NameGraph graph) {
        HashMap<String, Integer> trueFrequencies = new HashMap<String, Integer>();
        
        for(GraphNode node: graph.getNodes()) {
            if(!node.visited()) {
                int frequency = getComponentFrequency(node);
                String name = node.getName();
                trueFrequencies.put(name, frequency);
            }
        }
        
        return trueFrequencies;
    }
    
    // Do a DFS of a component to get the total frequency for this component
    private static int getComponentFrequency(GraphNode node) {
        if(node.visited()) {
            return 0;
        }
        
        node.setVisited(true);
        
        int sum = node.getFrequency();
        
        for(GraphNode neighbour : node.getNeighbours()) {
            sum += getComponentFrequency(neighbour);
        }
        
        return sum;
    }
    
    /*
     * Returns the maximum subsequence, where each element in the sequence
     * contains 2 items and each item must be less than its successor's items.
     * 
     * The items here are height and weight.
     * 
     * 1. Sort the array, this leaves us with a relative order for the longest subsequence
     * 2. For each element in the array... 
     *  2.1 Check if we can add it to a previous subsequence (is last element in subsequence < this)
     *  2.2 If we can, check if this subsequence is longer than the previous subsequence we could append
     *      this element on to, and if so set this subsequence as longest. Thus finding the longest
     *      subsequence for this element.
     *  2.3 Return the longest subsequence for this element
     * 3. If the longest subsequence for this element is longer than the longest found so far,
     *    update the longest subsequence to be this subsequence.
     * 
     * (CTCI 17.8: Circus Tower)
     * 
     * Performance: O(n^2)
     */
    public static ArrayList<HeightWeight> getLongestIncreasingSubsequence(ArrayList<HeightWeight> arr) {
        Collections.sort(arr);
        
        ArrayList<ArrayList<HeightWeight>> subsequences = new ArrayList<ArrayList<HeightWeight>>();
        ArrayList<HeightWeight> longestSequence = null;
        
        for(int i = 0; i < arr.size(); i++) {
            ArrayList<HeightWeight> longestAtIndex = getLongestAtIndex(arr, subsequences, i);
            subsequences.add(i, longestAtIndex);
            longestSequence = max(longestSequence, longestAtIndex);
        }
        
        return longestSequence;
    }
    
    private static ArrayList<HeightWeight> getLongestAtIndex(ArrayList<HeightWeight> arr, ArrayList<ArrayList<HeightWeight>> solutions, int index) {
        HeightWeight heightWeight = arr.get(index);
        
        ArrayList<HeightWeight> longestSequence = new ArrayList<HeightWeight>();
        
        for(int i = 0; i < index; i++) {
            ArrayList<HeightWeight> solution = solutions.get(i);
            if(canAppend(solution, heightWeight)) {
                longestSequence = max(solution, longestSequence);
            }
        }
        
        ArrayList<HeightWeight> best = (ArrayList<HeightWeight>) longestSequence.clone();
        best.add(heightWeight);
        
        return best;
    }
    
    private static boolean canAppend(ArrayList<HeightWeight> solution, HeightWeight value) {
        if(solution == null) {
            return false;
        }
        
        if(solution.size() == 0) {
            return true;
        }
        
        HeightWeight last = solution.get(solution.size()-1);
        return last.canComeBefore(value);
    }
    
    private static ArrayList<HeightWeight> max(ArrayList<HeightWeight> seq1, ArrayList<HeightWeight> seq2) {
        if(seq1 == null) {
            return seq2;
        } else if (seq2 == null) {
            return seq1;
        }
        
        return seq1.size() > seq2.size() ? seq1 : seq2;
    }
    
    /*
     * Finds the majority element (occurs > half elements) in an array
     * in O(n) time and O(1) space.
     * 
     * Works by using a kind of greedy approach -- assuming the element
     * that appears the most in a given "subarray" will be the majority element,
     * then runs through the array again to validate this.
     */
    public static int findMajorityElement(int [] arr) {
        int candidate = getCandidate(arr);
        
        if(isValid(arr, candidate)) {
            return candidate;
        } else {
            return -1;
        }
    }
    
    private static int getCandidate(int [] arr) {
        int majority = 0;
        int count = 0;
        
        for(int arrElement: arr) {
            if(count == 0) {
                // Either no majority element set
                // or majority element offset in subarray by mismatching elements
                // (In this case, the mismatching elements are also not the majority in that subarray)
                // Even though this means we discard the majority element at one point, we will find it again
                // later on if it exists.
                majority = arrElement;
            }
            
            if(arrElement == majority) {
                // Element matches our majority so far
                count++;
            } else {
                count--;
            }
        }
        
        return majority;
    }
    
    private static boolean isValid(int [] arr, int candidate) {
        int count = 0;
        
        for(int n: arr) {
            if(n == candidate) {
                count++;
            }
        }
        
        return count > arr.length / 2;
    }
    
    /*
     * BiNode: Convert a node with 2 child nodes
     * from a binary tree to a linked list.
     */
    public static NodePair convertToLinkedList(BiNode root) {
        if(root == null) {
            return null;
        }
        
        NodePair part1 = convertToLinkedList(root.node1);
        NodePair part2 = convertToLinkedList(root.node2);
        
        if(part1 != null) {
            concat(part1.previous, root);
        }
        
        if(part2 != null) {
            concat(root, part2.next);
        }
        
        return new NodePair(part1 == null ? root : part1.next, 
                            part2 == null ? root : part2.previous);
    }
    
    private static void concat(BiNode x, BiNode y) {
        x.node2 = y;
        y.node1 = x;
    }
    
    public static class NodePair {
        public BiNode next;
        public BiNode previous;
        
        NodePair(BiNode next, BiNode previous) {
            this.next = next;
            this.previous = previous;
        }
        

        public void printLinkedListTree(BiNode n) {
            for (BiNode node = n; node != null; node = node.node2) {
                if (node.node2 != null && node.node2.node1 != node) {
                    System.out.print("inconsistent node: " + node);
                }
                
                System.out.print(node.data + "->");
            }
            System.out.println();
        }
    }
}