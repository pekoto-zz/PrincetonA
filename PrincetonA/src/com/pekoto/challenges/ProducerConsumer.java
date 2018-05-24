package com.pekoto.challenges;


/*
 * A simple implementation of the producer-consumer problem.
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        Buffer sharedBuffer = new Buffer();
        
        Thread producer = new Producer(10, sharedBuffer);
        Thread consumer = new Consumer(10, sharedBuffer);
        
        producer.start();
        consumer.start();
    }
}

/*
 * A fixed-size buffer shared by
 * the producer and consumer.
 */
class Buffer {

    private int contents;
    private boolean empty = true; // Acts as semaphore

    public synchronized void put(int i) throws InterruptedException {
        
        // Wait until the buffer is empty
        while(!empty) {
            try {
                wait();
            } catch(InterruptedException ie) {
                throw ie;
            }
        }
        
        contents = i;
        empty = false;
        System.out.println("Put " + i + " into buffer...");
        
        // Notify a thread waiting on this object
        notify();
    }

    public synchronized int get() throws InterruptedException {
        
        // Wait until the buffer is full
        while(empty) {
            try {
                wait();
            } catch(InterruptedException ie) {
                throw ie;
            }
        }
        
        empty = true;
        notify();
        int value = contents;
        System.out.println("Got " + value + " from buffer...");
        
        return value;
    }
}

/*
 * Sleeps and puts an item on the queue
 */
class Producer extends Thread {
    private int numOfItemsToProduce;
    private Buffer sharedBuffer;
    
    public Producer (int numOfItemsToProduce, Buffer sharedBuffer) {
        this.numOfItemsToProduce = numOfItemsToProduce;
        this.sharedBuffer = sharedBuffer;
    }
    
    public void run() {
        for(int i=0; i < numOfItemsToProduce; i++) {
            
            try {
                Thread.sleep((int) Math.random() * 100);
            } catch (InterruptedException ie) {
                return;
            }
            
            try {
                sharedBuffer.put(i);
            } catch(InterruptedException ie) {
                return;
            }
        }
    }
}

/*
 * Gets an item from the queue and sleeps
 */
class Consumer extends Thread {
    
    private int numOfItemsToConsume;
    private Buffer sharedBuffer;
    
    public Consumer(int numOfItemsToConsume, Buffer sharedBuffer) {
        this.numOfItemsToConsume = numOfItemsToConsume;
        this.sharedBuffer = sharedBuffer;
    }
    
    public void run() {
        int value;
        
        for(int i = 0; i < numOfItemsToConsume; i++) {
            try {
                value = sharedBuffer.get();
            } catch (InterruptedException ie) {
                return;
            }
            
            try {
                Thread.sleep((int) Math.random() * 100);
            } catch (InterruptedException ie) {
                return;
            }
        }
    }
}