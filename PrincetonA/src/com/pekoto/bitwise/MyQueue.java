package com.pekoto.bitwise;

import java.util.NoSuchElementException;

/*
 * CTCI queue class
 */
public class MyQueue<T> {
    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;
        
        public QueueNode(T data) {
            this.data = data;
        }
    }
    
    private QueueNode<T> first;
    private QueueNode<T> last;
    
    public void add(T item) {
        QueueNode<T> t = new QueueNode<T>(item);
        
        if(last != null) {
            last.next = t;
        }
        
        last = t;
        
        if(first == null) {
            first = last;
        }
    }
    
    public T remove() {
        if (first == null) {
            throw new  NoSuchElementException("Can't remove from empty queue");
        }
        
        T data = first.data;
        first = first.next;
        
        if(first == null) {
            last = null;
        }
        
        return data;
    }
    
    public T peek() {
        if(first == null) {
            throw new NoSuchElementException("Can't peek empty queue");
        }
        
        return first.data;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    
}
