package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.pekoto.datastructures.RingBufferCircularQueue;

/**
 * Unit tests for the RingBufferCircularQueue class.
 */
public class RingBufferCircularQueueTests {

    @Test
    public void testEnqueueDequeue_NoWrapAround() {
        RingBufferCircularQueue<String> ringBuffer = new RingBufferCircularQueue<String>(4);
        
        ringBuffer.enqueue("One");
        ringBuffer.enqueue("Two");
        ringBuffer.enqueue("Three");
        ringBuffer.enqueue("Four");
        
        assertEquals("One", ringBuffer.dequeue());
        assertEquals("Two", ringBuffer.dequeue());
        assertEquals("Three", ringBuffer.dequeue());
        assertEquals("Four", ringBuffer.dequeue());
    }
    
    @Test
    public void testEnqueueDequeue_WrapAround() {
        RingBufferCircularQueue<String> ringBuffer = new RingBufferCircularQueue<String>(4);
        
        ringBuffer.enqueue("One");
        ringBuffer.enqueue("Two");
        
        ringBuffer.dequeue();
        
        ringBuffer.enqueue("Three");
        ringBuffer.enqueue("Four");
        
        ringBuffer.dequeue();
        ringBuffer.dequeue();
        
        ringBuffer.enqueue("Five");
        
        assertEquals("Four", ringBuffer.dequeue());
        assertEquals("Five", ringBuffer.dequeue());
    }
    
    @Test
    public void testEnqueueFull() {
        RingBufferCircularQueue<String> ringBuffer = new RingBufferCircularQueue<String>(4);
        
        ringBuffer.enqueue("One");
        ringBuffer.enqueue("Two");
        ringBuffer.enqueue("Three");
        ringBuffer.enqueue("Four");
        
        assertFalse(ringBuffer.enqueue("Five"));
    }
    
    @Test
    public void testDequeueEmpty() {
        RingBufferCircularQueue<String> ringBuffer = new RingBufferCircularQueue<String>(4);

        try {
            ringBuffer.dequeue();
            Assertions.fail("Failed to throw exception when empty queue dequeued");
        } catch(NullPointerException npe) {
            Assertions.assertTrue(true);
        }
    }
}
