package com.pekoto.challenges;

/*
 * CTCI stack class
 */
public class MyStack<T> {
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;
        
        public StackNode(T data) {
            this.data = data;
        }
    }
    
    private StackNode<T> top;
    
    public T pop() {
        if(top == null) {
            throw new NullPointerException("Can't pop empty stack");
        }
        
        T item = top.data;
        top = top.next;
        
        return item;
    }
    
    public void push(T item) {
        StackNode<T> t = new StackNode<T>(item);
        t.next = top;
        top = t;
    }
    
    public T peek() {
        if (top == null) {
            throw new NullPointerException("Can't peek empty stack");
        }
        
        return top.data;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
}
