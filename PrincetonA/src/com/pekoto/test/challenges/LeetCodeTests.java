package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pekoto.challenges.LeetCode;
import com.pekoto.challenges.MedianFinder;
import com.pekoto.challenges.TreeCodec;
import com.pekoto.challenges.TreeNode;

/**
 *  Unit tests for the LeetCode class 
 */
public class LeetCodeTests {
    
    @Test
    public void testLengthOfLongestSubstring() {
        String s = "abcbadcab";
        
        assertEquals(4, LeetCode.lengthOfLongestSubstring(s));
    }
    
    @Test
    public void testMedianSortedArrays() {
        int [] arr1 = {1, 2};
        int [] arr2 = {3, 4};
        
        assertEquals(2.5, LeetCode.findMedianSortedArrays(arr1, arr2), 0.1);
    }
    
    @Test
    public void testMySqrt() {
        assertEquals(2, LeetCode.mySqrt(8));
        assertEquals(3, LeetCode.mySqrt(9));
    }
    
    @Test
    public void testIsMatch() {
        assertTrue(LeetCode.isMatch("aaa", "a*"));
    }
    
    @Test
    public void testMerge() {
        
        List<Interval> intervals = new ArrayList<Interval>();
        
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(0, 2));
        intervals.add(new Interval(3, 5));
        
        List<Interval> merged = LeetCode.merge(intervals);
        
        assertTrue(true);
    }
    
    @Test
    public void testFastPow() {
        assertEquals(16.0, LeetCode.myPow(2.0, 4), 0.1);
        assertEquals(0.25, LeetCode.myPow(2.0, -2), 0.1);
    }
    
    @Test
    public void testRemoveDuplicateLetters() {
        assertEquals("bac", LeetCode.removeDuplicateLetters("bbacac"));
    }
    
    public void testLongestConsecutive() {
    	assertEquals(4, LeetCode.longestConsecutive(new int[] {100, 2, 200, 4, 1, 3}));
    }
    
    @Test
    public void testNumSquares() {
    	assertEquals(3, LeetCode.numSquares(12));
    	assertEquals(2, LeetCode.numSquares(13));
    }
    
    @Test
    public void testCalculator() {
        assertEquals(23, LeetCode.calculator("(1+(4+5+2)-3)+(6+8)"));
    }
    
    @Test
    public void testTreeCode() {
        
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        
        three.setLeftChild(four);
        three.setRightChild(five);
        root.setLeftChild(two);
        root.setRightChild(three);
        
        TreeCodec treeCodec = new TreeCodec();
        String serializedTree = treeCodec.serialize(root);
        TreeNode deserialized = treeCodec.deserialize(serializedTree);
        
        assertEquals(1, deserialized.val);
        assertEquals(2, deserialized.left.val);
        assertEquals(3, deserialized.right.val);
        assertEquals(4, deserialized.right.left.val);
        assertEquals(5, deserialized.right.right.val);
    }
    
    @Test
    public void testFirstMissingPositve() {
        LeetCode lc = new LeetCode();

        assertEquals(3, lc.firstMissingPositive(new int[] {2, 1, 0}));
    }
    
    @Test
    public void testFractionToDecimal() {
    	assertEquals("0.(012)", LeetCode.fractionToDecimal(4, 333));
    }
    
    @Test
    public void testCountPrimes() {
        assertEquals(4, LeetCode.countPrimes(10));
    }
    
    @Test
    public void testNumDecodings() {
    	assertEquals(1, LeetCode.numDecodings("10"));
    	assertEquals(3, LeetCode.numDecodings("226"));
    	assertEquals(0, LeetCode.numDecodings("230"));
    }
    
    @Test
    public void testPermute() {
    	LeetCode.getPermutations(new int[] {1, 2, 3});
    }
    
    @Test
    public void testGetPermutations() {
        LeetCode.getPermutations("ABC");
    }
    
    @Test
    public void testPermutations() {
        LeetCode.permutations(new int [] {1, 2, 3});
    }
    
    @Test
    public void testLadderLength() {
        List<String> dic = new ArrayList<String>();
        dic.add("hot");
        dic.add("dot");
        dic.add("dog");
        dic.add("lot");
        dic.add("log");
        dic.add("cog");
        
        LeetCode lt = new LeetCode();
        
        assertEquals(5, lt.ladderLength("hit", "cog", dic));
    }
    
    @Test
    public void testMaxNonAdjacentSum() {
        assertEquals(12, LeetCode.maxNonAdjacentSum(new int[] {2, 7, 9, 3, 1}));
    }
    
    @Test
    public void testMedianFinder() {
        MedianFinder mf = new MedianFinder();
        
        mf.addNum(2);
        mf.addNum(3);
        mf.addNum(4);
    }
    
    @Test
    public void testLowestCommonAncestor() {
    	TreeNode root = new TreeNode(3);
    	TreeNode one = new TreeNode(1);
    	TreeNode five = new TreeNode(5);
    	TreeNode six = new TreeNode(6);
    	TreeNode two = new TreeNode(2);
    	TreeNode seven = new TreeNode(7);
    	TreeNode four = new TreeNode(4);
    	TreeNode zero = new TreeNode(0);
    	TreeNode eight = new TreeNode(8);
    	
    	root.setLeftChild(five);
    	root.setRightChild(one);
    	one.setLeftChild(zero);
    	one.setRightChild(eight);
    	five.setLeftChild(six);
    	five.setRightChild(two);
    	two.setLeftChild(seven);
    	two.setRightChild(four);
    	
    	LeetCode lt = new LeetCode();
    	
    	lt.lowestCommonAncestor(root, five, four);
    }
    
    @Test
    public void testExists() {
    	char[][] board = {
    			{'a', 'b'},
    			{'c', 'd'}
    	};
    	
    	LeetCode lc = new LeetCode();
    	
    	assertTrue(lc.exists(board, "acdb"));
    }
    
    @Test
    public void testMaxProduct() {
        
        int[] nums = { 2, 3, -2, 4};
        
        LeetCode lc = new LeetCode();
        
        assertEquals(6, lc.maxProduct(nums));
    }
    
    @Test
    public void testFindWords() {
        String [] dict = {
                "oath",
                "pea",
                "eat",
                "rain"
        };
        
        char [][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        
        LeetCode lc = new LeetCode();
        
        assertEquals(2, lc.findWords(board, dict).size());
    }
    
    @Test
    public void testCountSmaller() {
        int [] nums = {5, 2, 6, 1};
        
        LeetCode lc = new LeetCode();
        
        List<Integer> results = lc.countSmaller(nums);
        
        assertEquals(4, results.size());
    }
    
    @Test
    public void testSortColors() {
        int [] nums = {2, 0, 2, 1, 1, 0};
        
        LeetCode lc = new LeetCode();
        
        lc.sortColors(nums);
    }
    
    @Test
    public void testCanJump() {
        int [] possible = {2, 3, 1, 1, 4};
        int [] impossible = {3, 2, 1, 0, 4};
        
        LeetCode lc = new LeetCode();
        
        assertTrue(lc.canJump(possible));
        assertFalse(lc.canJump(impossible));
    }
    
    @Test
    public void testMissingNumber() {
        LeetCode lc = new LeetCode();
        
        assertEquals(2, lc.missingNumber(new int [] {3, 0, 1}));
    }
    
    @Test
    public void testLargestNumber() {
        LeetCode lc = new LeetCode();
        
        int [] nums = {3,30,34};
        
        assertEquals("34330", lc.largestNumber(nums));
    }
    
    @Test
    public void testFindOrder() {
    	int [][] nums = {
    			{1, 0}
    	};
    	
    	LeetCode lc = new LeetCode();
    	
    	int [] result = lc.findOrder(2, nums);
    	
    	assertEquals(0, result[0]);
    	assertEquals(1, result[1]);
    }
    
    @Test
    public void testInorderSuccessor() {
    	TreeNode root = new TreeNode(2);
    	TreeNode one = new TreeNode(1);
    	TreeNode three = new TreeNode(3);
    	
    	root.left = one;
    	root.right = three;
    	
    	LeetCode lc = new LeetCode();
    	
    	assertEquals(root, lc.inorderSuccessor(root, one));
    }
    
    @Test
    public void testInorderPredecessor() {
    	TreeNode root = new TreeNode(5);
    	TreeNode one = new TreeNode(1);
    	TreeNode two = new TreeNode(2);
    	TreeNode three = new TreeNode(3);
    	TreeNode four = new TreeNode(4);
    	TreeNode six = new TreeNode(6);
    	
    	root.right = six;
    	root.left = three;
    	three.left = two;
    	three.right = four;
    	two.left = one;

    	LeetCode lc = new LeetCode();
    	
    	assertEquals(null, lc.inorderPredecessor(root, one));
    	assertEquals(one, lc.inorderPredecessor(root, two));
    	assertEquals(two, lc.inorderPredecessor(root, three));
    	assertEquals(three, lc.inorderPredecessor(root, four));
    	assertEquals(four, lc.inorderPredecessor(root, root));
    	assertEquals(root, lc.inorderPredecessor(root, six));


    }
}
