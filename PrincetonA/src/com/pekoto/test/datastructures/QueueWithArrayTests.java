package com.pekoto.test.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pekoto.datastructures.QueueWithArray;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the QueueWithArray class
 */
public class QueueWithArrayTests {

    @Test
    public void testEnqueue() {
        QueueWithArray<String> queue = new QueueWithArray<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals(3, queue.size());
        assertEquals("A", queue.peekStart());
        assertEquals("C", queue.peekEnd());
    }

    @Test
    public void testDequeue() {
        QueueWithArray<String> queue = new QueueWithArray<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.peekStart());
        assertEquals("C", queue.peekEnd());
        assertEquals(2, queue.size());
    }

    @Test
    public void testDequeueAll() {
        QueueWithArray<String> queue = new QueueWithArray<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueAllRequeue() {
        QueueWithArray<String> queue = new QueueWithArray<>();

        queue.enqueue("A");

        assertEquals("A", queue.dequeue());

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        queue.enqueue("B");
        assertEquals(1, queue.size());

        assertEquals("B", queue.dequeue());
    }

    @Test
    public void testDequeueEmptyQueue() {
        QueueWithArray<String> queue = new QueueWithArray<>();

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
    public void testResize() {
        QueueWithArray<String> queue = new QueueWithArray<>(3);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");

        assertEquals(5, queue.size());
    }
}
