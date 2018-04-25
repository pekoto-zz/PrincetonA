package com.pekoto.challenges;

import java.util.HashSet;
import java.util.Stack;

public class LinkedLists {

    /**
     * Removes duplicate nodes from a linked list
     * 
     * It's possible to do this in O(1) space by using another
     * node to look ahead at the rest of the list for every node,
     * but this increases performance to O(n^2).
     * 
     * Performance: O(n)
     * Space: O(n)
     * 
     * @param linkedListNode
     */
    public static void removeDuplicates(LinkedListNode linkedListNode) {
        LinkedListNode previous = linkedListNode;
        LinkedListNode next = previous.getNext();
        
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(previous.getValue());
        
        while(next != null) {
            if(set.contains(next.getValue())) {
                previous.setNext(next.getNext());
                next = next.getNext();
            } else {
                set.add(next.getValue());
                previous = next;
                next = previous.getNext();
            }
        }
    }
    
    /**
     * Returns the kth element from the end of a linked list
     * (the last element is 1).
     * 
     * We could also do this recursively.
     * We could read through the whole list first, which
     * would be slightly less efficient but still O(n).
     * 
     * Performance: O(n)
     * Space: O(1)
     * 
     * @param linkedListNode
     * @param k
     * @return The kth last element (last element = 1)
     */
    public static Integer getKthElement(LinkedListNode linkedListNode, int k) {
        LinkedListNode p1 = linkedListNode;
        LinkedListNode p2 = linkedListNode;
    
        for(int i=0; i < k; i++) {
            if(p1 == null) {
                // k is greater than the length of the linked list
                return null;
            }
            
            p1 = p1.getNext();
        }
        
        // p1 will iterate length-k times.
        while(p1 != null) {
            p1 = p1.getNext();
            p2 = p2.getNext();
        }
        
        return p2.getValue();
    }
    
    /**
     * Deletes a node from a singly linked list when
     * only given access to that node.
     * 
     * Copy the value from the next node,
     * and then delete the next node.
     * 
     * Performance: O(1)
     * Space: O(1)
     * 
     * @param linkedListNode
     */
    public static void deleteMiddleNode(LinkedListNode linkedListNode) {
        if(linkedListNode == null) {
            throw new NullPointerException("Can't delete null node");
        }
        
        if(linkedListNode.getNext() == null) {
            throw new NullPointerException("Can't delete last node");
        }
        
        LinkedListNode next = linkedListNode.getNext();
        
        // Copy value of next node into this node
        linkedListNode.setValue(next.getValue());
        
        // Delete next node
        linkedListNode.setNext(next.getNext());
    }
    
    /**
     * Partitions a linked list around 'partition' such that
     * all < elements are on the left.
     * 
     * CTCI uses multiple lists and joins them together.
     * We can do it slightly more efficiently by using a quicksort-like
     * partition scheme or swapping lesser and greater elements.
     * 
     * Performance: O(n)
     * Space: O(1)
     * 
     * @param linkedListNode
     * @param partition
     */
    public static void partition(LinkedListNode linkedListNode, int partition) {
        if(linkedListNode == null) {
            throw new NullPointerException("Can't partition empty list");
        }
        
        LinkedListNode greaterThanOrEqualPointer = linkedListNode;
        LinkedListNode lessThanPointer;
        
        // Find the first element >= partition value
        while(greaterThanOrEqualPointer.getValue() < partition) {
            greaterThanOrEqualPointer = greaterThanOrEqualPointer.getNext();
        }
        
        lessThanPointer = greaterThanOrEqualPointer.getNext();
        
        while(lessThanPointer != null) {
            if(lessThanPointer.getValue() < partition) {
                int temp = greaterThanOrEqualPointer.getValue();
                greaterThanOrEqualPointer.setValue(lessThanPointer.getValue());
                lessThanPointer.setValue(temp);
                greaterThanOrEqualPointer = greaterThanOrEqualPointer.getNext();
            }
            lessThanPointer = lessThanPointer.getNext();
        }
    }
    
    /**
	 * Given two linked lists representing integers in reverse order,
	 * return a linked list representing the sum in reverse order.
	 * 
	 * Performance: O(n+m)
	 * Space: O(n+m) (due to recursive calls)
	 * 
	 * @param nodeOne
	 * @param nodeTwo
	 * @return The sum of the two nodes, reversed, in a linked list
	 */
	public static LinkedListNode addInReverse(LinkedListNode nodeOne, LinkedListNode nodeTwo) {
		return addInReverse(nodeOne, nodeTwo, 0);
	}
	
	private static LinkedListNode addInReverse(LinkedListNode nodeOne, LinkedListNode nodeTwo, int carry) {
		
		if(nodeOne == null && nodeTwo == null && carry == 0) {
			return null;
		}
		
		int value = carry;
		
		if(nodeOne != null) {
			value += nodeOne.getValue();
		}
		
		if(nodeTwo != null) {
			value += nodeTwo.getValue();
		}
		
		LinkedListNode result = new LinkedListNode(value%10);
		
		if(nodeOne != null || nodeTwo != null) {
			LinkedListNode next = addInReverse(nodeOne == null ? null : nodeOne.getNext(),
									  		   nodeTwo == null ? null: nodeTwo.getNext(),
									  		   value >= 10 ? 1 : 0);
			
			result.setNext(next);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the linked list is a palindrome.
	 * 
	 * We can also do this by reversing the linked list,
	 * but this method shows a helpful technique:
	 * using slow and fast pointers to find the middle of a linked list.
	 * 
	 * Performance: O(n)
	 * Space: O(n)
	 * 
	 * @param linkedListNode
	 * @return True if the linked list is a palindrome
	 */
	public static boolean isPalindrome(LinkedListNode linkedListNode) {
	    
	    LinkedListNode slowPointer = linkedListNode;
	    LinkedListNode fastPointer = linkedListNode;
	    
	    Stack<Integer> stack = new Stack<Integer>();
	    
	    while(fastPointer != null && fastPointer.getNext() != null) {
	        stack.push(slowPointer.getValue());
	        slowPointer = slowPointer.getNext();
	        fastPointer = fastPointer.getNext().getNext();
	    }
	    
	    // Linked list has uneven number of nodes
	    // Skip middle node
	    if(fastPointer != null) {
	        slowPointer = slowPointer.getNext();
	    }
	    
	    while(slowPointer != null) {
	        if(slowPointer.getValue() != stack.pop()) {
	            return false;
	        }
	        
	        slowPointer = slowPointer.getNext();
	    }
	    
	    return true;   
	}
}
