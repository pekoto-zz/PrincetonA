package com.pekoto.test.datastructures;

import org.junit.jupiter.api.Test;
import com.pekoto.datastructures.StackWithArray;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the StackWithArray class
 */
public class StackWithArrayTests {

    @Test
    public void testPush() {
        StackWithArray<String> stack = new StackWithArray<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals("C", stack.peek());
        assertEquals(3, stack.size());
    }

    @Test
    public void testPop() {
        StackWithArray<String> stack = new StackWithArray<>();

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
        StackWithArray<String> stack = new StackWithArray<>();

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
    void testResize() {
        StackWithArray<String> stack = new StackWithArray<>(3);

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");

        assertEquals(5, stack.size());
    }

    @Test
    void testIterator() {
        StackWithArray<String> stack = new StackWithArray<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        StringBuilder sb = new StringBuilder();

        for(String s: stack) {
            sb.append(s);
        }

        assertEquals("CBA", sb.toString());
    }
}
