package com.pekoto.challenges;

import java.util.HashSet;

public class LinkedLists {

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
}
