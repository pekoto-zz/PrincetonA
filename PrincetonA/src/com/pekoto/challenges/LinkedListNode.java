package com.pekoto.challenges;

import java.util.ArrayList;

public class LinkedListNode {

    private int value;
    private LinkedListNode next;
    
    public LinkedListNode(int value) {
        this.value = value;
    }
    
    public void appendToTail(int value) {
        LinkedListNode newTail = new LinkedListNode(value);
        LinkedListNode thisNode = this;
        
        while(thisNode.next != null) {
            thisNode = thisNode.next;
        }
        
        thisNode.next = newTail;
    }
    
    public int getValue() {
        return value;
    }
    
    public LinkedListNode getNext() {
        return next;
    }
    
    public void setNext(LinkedListNode next) {
        this.next = next;
    }
    
    public ArrayList<Integer> toArrayList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        LinkedListNode node = this;
        
        while(node != null) {
            list.add(node.getValue());
            node = node.getNext();
        }
        
        return list;
    }
    
    @Override
    public String toString() {
        return ""+value;
    }
}
