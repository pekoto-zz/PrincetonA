package com.pekoto.test.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pekoto.datastructures.QueueWithLinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the QueueWithLinkedList class
 */
public class QueueWithLinkedListTests {

    @Test
    public void testEnqueue() {
        QueueWithLinkedList<String> queue = new QueueWithLinkedList<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals(3, queue.size());
        assertEquals("A", queue.peekStart());
        assertEquals("C", queue.peekEnd());
    }

    @Test
    public void testDequeue() {
        QueueWithLinkedList<String> queue = new QueueWithLinkedList<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueEmpty() {
        QueueWithLinkedList<String> queue = new QueueWithLinkedList<>();

        queue.enqueue("A");

        queue.dequeue();

        try {
            queue.dequeue();
            Assertions.fail("Failed to throw exception when empty queue dequeued");
        } catch(NullPointerException npe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testIterator() {
        QueueWithLinkedList<String> queue = new QueueWithLinkedList<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        StringBuilder sb = new StringBuilder();

        for(String s: queue) {
            sb.append(s);
        }

        assertEquals("ABC", sb.toString());
    }

}
