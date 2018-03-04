package com.pekoto.test.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pekoto.datastructures.StackWithLinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the StackWithLinkedList class
 */
public class StackWithLinkedListTests {

    @Test
    void testPush() {
        StackWithLinkedList<String> stack = new StackWithLinkedList<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals("C", stack.peek());
        assertEquals(3, stack.size());
    }

    @Test
    void testPop() {
        StackWithLinkedList<String> stack = new StackWithLinkedList<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals("C", stack.pop());
        assertEquals(2, stack.size());
    }

    @Test
    void testPopAll() {
        StackWithLinkedList<String> stack = new StackWithLinkedList<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopEmpty() {
        StackWithLinkedList<String> stack = new StackWithLinkedList<>();

        stack.push("A");

        stack.pop();

        try {
            stack.pop();
            Assertions.fail("Failed to throw exception when empty stack popped");
        } catch(NullPointerException npe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void testIterator() {
        StackWithLinkedList<String> stack = new StackWithLinkedList<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        StringBuffer sb = new StringBuffer();

        for(String s: stack) {
            sb.append(s);
        }

        assertEquals("CBA", sb.toString());
    }
}
