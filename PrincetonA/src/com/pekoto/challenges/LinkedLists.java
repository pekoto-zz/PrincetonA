package com.pekoto.challenges;

import java.util.HashSet;

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
}
