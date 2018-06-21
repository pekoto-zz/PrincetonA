package com.pekoto.challenges;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    /*
     * Prints the longest word that can be made out of other words in a list.
     * 
     * 1. Add all words to a map
     * 2. Sort the words by length (so we can get the longest one and break early)
     * 3. For each of our words, check if we can build it from other words:
     *  3.1 Get the left substring from 0...i
     *  3.2 Get the right substring from i...string.length
     *  3.3 If the map contains the left substring...
     *  3.4 Check if we can build the right substring using the same process(recursive call)
     *  3.5 If we can return true
     * 4. Return this word, as it's the longest
     * 
     * Note: We can't build a word from itself.
     *       If we can't build a word, we set a 'false'
     *       value in the map to effectively memoize if
     *       we can build the same word or not.
     *       To stop this memoization catching the original word,
     *       we have a flag to check we are not checking the original word.
     */
    public static String getLongestWordFromList(String [] arr) {
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        
        for(String str: arr) {
            map.put(str, true);
        }
        
        Arrays.sort(arr, new LengthComparator());
        
        for(String str: arr) {
            if(canBuildWord(str, true, map)) {
                return str;
            }
        }
        
        return "";
    }
    
    private static boolean canBuildWord(String str, boolean isOriginalWord, HashMap<String, Boolean> map) {
        if(!isOriginalWord && map.containsKey(str)) {
            return map.get(str);
        }
        
        for(int i = 1; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i, str.length());
            
            // Note order of params is important here:
            // We won't recurse until left half makes a word
            // Essentially, wait until we can build the left half, then check the right half, repeat
            if(map.containsKey(left) && map.get(left) && canBuildWord(right, false, map)) {
                return true;
            }
        }
        
        map.put(str, false);
        return false;
    }
    
    /* 
     * Max sum non-adjacent (CTCI 17.16)
     * Returns the max sum from an array, where you can't take adjacent elements.
     * 
     * Point:
     * Since the elements can't be adjacent, the max sum for any given element
     * will either be this element + the element 1 away, or the max sum of the adjacent
     * element (the element 1 away).
     * 
     * Performance: O(n)
     * Space: O(1)
     */
    public static int getMaxSumNonAdjacent(int [] arr) {
        int oneAway = 0;
        int twoAway = 0;
        
        for(int i = arr.length-1; i >= 0; i--) {
            int bestWith = arr[i] + twoAway;
            int bestWithout = oneAway;
            int current = Math.max(bestWith, bestWithout);
            twoAway = oneAway;
            oneAway = current;
        }
        
        return oneAway;
    }
    
    /*
     * Find several smaller strings within a larger string.
     * 
     * 1. Create a trie from the smaller strings O(largest_s*num_of_s)
     * 	1.1 For each smaller string
     * 	1.2. If it's < big string size, add it to trie
     * 2. Search the trie with the larger string O(largest_s*size_of_larger_string)
     *	2.1 For each index of the larger string 0...i, take that as the start
     *		3.1 Take the char at this start index from the larger string
     *		3.2 Try to get this child by searching the root trie node's children using this char as key
     *		3.3 If the child is null, return
     *		3.4 If the child terminates, add this string to a list of strings -- we found it!
     *		3.5 Move to the next char in the big string, and update root to be the child
     *	2.2 Add all of these strings to a HashMap, which lists the string and a list of indices where it
     *		was found (use the start index from 2.1)
     * 3. Pass back the list of strings and their starting indices 
     *
     *	Performance: O(largest_s*num_of_s + largest_s*size_of_larger_string)
     *	Memory: O(largest_s*num_of_s) (for trie)
     */
    public static HashMap<String, ArrayList<Integer>> searchBigStringForSmalls(String big, String[] smalls) {
        HashMap<String, ArrayList<Integer>> lookup = new HashMap<String, ArrayList<Integer>>();
        
        // The big.length() check is just a sanity check:
        // If a small is bigger than the big string, it can't possible be contained in the big string
        TrieNode root = createTrieFromStrings(smalls, big.length()).getRoot();
    
        for(int i = 0; i < big.length(); i++) {
            ArrayList<String> strings = findStringsAtLocation(root, big, i);
            insertIntoHashMap(strings, lookup, i);
        }
        
        return lookup;
    }
    
    public static Trie createTrieFromStrings(String[] smalls, int maxSize) {
        Trie trie = new Trie();
        
        for(String s: smalls) {
            if(s.length() <= maxSize) {
                trie.insertString(s);
            }
        }
        
        return trie;
    }
    
    public static ArrayList<String> findStringsAtLocation(TrieNode root, String big, int start) {
        ArrayList<String> strings = new ArrayList<String>();
        
        int index = start;
        while(index < big.length()) {
            root = root.getChild(big.charAt(index));
            
            if(root == null) {
                break;
            }
            
            if(root.terminates()) {
                strings.add(big.substring(start, index+1));
            }
            
            index++;
        }
        
        return strings;
    }
    
    public static void insertIntoHashMap(ArrayList<String> strings, HashMap<String, ArrayList<Integer>> map, int index) {
        for(String s: strings) {
            if(!map.containsKey(s)) {
                map.put(s, new ArrayList<Integer>());
            }
            map.get(s).add(index);
        }
    }
    
    /* 
     * Returns the max submatrix from an NxN matrix
     * 
     * 1. Starting from every row...
     *      2. From that row until the end of the matrix...
     *          3. Starting from every column...
     *              4. From that column until the end of the matrix...
     *                 - Iterate over from start row to end row, and start col to end col, to generate that submatrix.
     *                 
     * Will generate submatrices from top-left only, top-left > col+1, top-left > col > col+n...etc.
     * Then top middle, top middle > col+1, etc.
     * Then top left to row+1, top left to row+1 > col+1, etc.
     * 
     * Performance: O(N^6) -- O(N^4) submatrices + O(N^2) time to compute the area for each
     * 
     * (There is a more optimal solution using the maximum subarray approach, but I added this
     *  as a reference to show how to brute-force generate all submatrices from a matrix)
     * 
     */
    public static Submatrix getMaxSubmatrix(int[][] matrix) {
        
        Submatrix max = null;
        
        for(int startRow = 0; startRow < matrix.length; startRow++) {
            for(int endRow = startRow; endRow < matrix.length; endRow++) {
                for(int startCol = 0; startCol < matrix[0].length; startCol++) {
                   for(int endCol = startCol; endCol < matrix[0].length; endCol++) {
                       int sum = getSubmatrixSum(matrix, startRow, endRow, startCol, endCol);
                       
                       if(max == null || sum > max.sum()) {
                           max = new Submatrix(startRow, endRow, startCol, endCol, sum);
                       }
                   }
                }
            }
        }
        
        return max;
    }
    
    private static int getSubmatrixSum(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        int sum = 0;
        
        for(int row = startRow; row <= endRow; row++) {
            for(int col = startCol; col <= endCol; col++) {
                sum += matrix[row][col];
            }
        }
        
        return sum;
    }
}
