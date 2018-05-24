package com.pekoto.challenges;

/*
 * Runs FizzBuzz using multiple threads
 */
public class MultithreadedFizzBuzz {
    public static void main(String[] args) {
        Thread [] threads = {
                new FizzBuzzThread(true, true, 100, "FizzBuzz"),
                new FizzBuzzThread(true, false, 100, "Fizz"),
                new FizzBuzzThread(false, true, 100, "Buzz"),
                new NumberThread(100)
        };
        
        for(Thread thread: threads) {
            thread.start();
        }
    }
}

class FizzBuzzThread extends Thread {
    // Acts as semaphore on all instances of FizzBuzzThread
    private static Object lock = new Object();
    
    protected static int current = 1;
    private int max;
    private boolean divByThreeThread;
    private boolean divByFiveThread;
    private String toPrint;
    
    public FizzBuzzThread(boolean divByThreeThread, boolean divByFiveThread, int max, String toPrint) {
        this.divByThreeThread = divByThreeThread;
        this.divByFiveThread = divByFiveThread;
        this.max = max;
        this.toPrint = toPrint;
    }
    
    public void print() {
        System.out.println(toPrint +": " + current);
    }
    
    public void run() {
        while(true) {
            // Ensure only 1 thread can increment current at a time
            synchronized(lock) {
                
                if(current > max) {
                    return;
                }
                
                if((current % 3 == 0) == divByThreeThread &&
                   (current % 5 == 0) == divByFiveThread) {
                    print();
                    current++;
                }
                    
            }
        }
    }
}

class NumberThread extends FizzBuzzThread {
    public NumberThread(int max) {
        super(false, false, max, null);
    }
    
    public void print() {
        System.out.println(current);
    }
}