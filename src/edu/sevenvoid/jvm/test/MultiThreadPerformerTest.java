/**
 * 
 */
package edu.sevenvoid.jvm.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Loops
 *
 */

// http://flex4java.blogspot.hk/2015/03/is-multi-threading-really-worth-it.html

// 并发编程所带来的开销比较

public class MultiThreadPerformerTest {
	
	public static void main(String[] args) {
	    Counter counter = new Counter(0);
	    long startTime = System.currentTimeMillis();
	    for (long index = 0; index<1000000000L; index++) {
	        counter.incrementByOne();
	    }
	    long endTime = System.currentTimeMillis();
	    System.out.println("counter Time taken: " + (endTime - startTime) + " ms");
	    System.out.println("counter Value is: " + counter.getCount());
	    
	    CounterVolatile counterVolatile = new CounterVolatile(0);
	    startTime = System.currentTimeMillis();
	    for (long index = 0; index<1000000000L; index++) {
	    	counterVolatile.incrementByOne();
	    }
	    endTime = System.currentTimeMillis();
	    System.out.println("counterVolatile Time taken: " + (endTime - startTime) + " ms");
	    System.out.println("counterVolatile Value is: " + counterVolatile.getCount());
	    
	    CounterAtomic counterAtomic = new CounterAtomic();
        startTime = System.currentTimeMillis();
        for (long index = 0; index<1000000000L; index++) {
        	counterAtomic.incrementByOne();
        }
        endTime = System.currentTimeMillis();
        System.out.println("counterAtomic Time taken: " + (endTime - startTime) + " ms");
        System.out.println("counterAtomic Value is: " + counterAtomic.getCount());
        
        
        CounterLock counter11 = new CounterLock(0);
        Lock lock = new ReentrantLock();
        startTime = System.currentTimeMillis();
        for (long index = 0; index < 1000000000L; index++) {
            lock.lock();
            try {
            	counter11.incrementByOne();
            } finally {
                lock.unlock();
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("CounterLock Time taken: " + (endTime - startTime) + " ms");
        System.out.println("CounterLock Value is: " + counter11.getCount());
        
//        CounterAtomic multiThreadCounterAtomic = new CounterAtomic();
//        
//        Thread thread1 = new Thread(() -> {
//            for (long index = 0; index < 500000000L; index++) {
//            	multiThreadCounterAtomic.incrementByOne();
//            }
//        });
//        Thread thread2 = new Thread(() -> {
//            for (long index = 0; index < 500000000L; index++) {
//            	multiThreadCounterAtomic.incrementByOne();
//            }
//        });
//        startTime = System.currentTimeMillis();
// 
//        thread1.start();
//        thread2.start();
//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("multiThreadCounterAtomic Time taken: " + (endTime - startTime) + " ms");
//        System.out.println("multiThreadCounterAtomic Value is: " + multiThreadCounterAtomic.getCount());
      
        CounterMultiThreadLock counterMultiThreadLock1 = new CounterMultiThreadLock(0);
        CounterMultiThreadLock counterMultiThreadLock2 = new CounterMultiThreadLock(0);
        startTime = System.currentTimeMillis();
        Thread thread00 = new Thread(counterMultiThreadLock1);
        Thread thread01 = new Thread(counterMultiThreadLock2);
        thread00.start();
        thread01.start();
        try{
        	thread00.join();
        	thread01.join();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("counterMultiThreadLock Time taken: " + (endTime - startTime) + " ms");
        System.out.println("counterMultiThreadLock Value is: " + counterMultiThreadLock1.getCount());
        
//        Counter multiThreadCounterLock = new Counter(0);
//        Lock lock1 = new ReentrantLock();
// 
//        Thread thread3 = new Thread(() -> {
//            for (long index = 0; index < 500000000L; index++) {
//            	lock1.lock();
//                try {
//                	multiThreadCounterLock.incrementByOne();
//                } finally {
//                	lock1.unlock();
//                }
//            }
//        });
// 
//        Thread thread4 = new Thread(() -> {
//            for (long index = 0; index < 500000000L; index++) {
//            	lock1.lock();
//                try {
//                	multiThreadCounterLock.incrementByOne();
//                } finally {
//                	lock1.unlock();
//                }
//            }
//        });
// 
//        startTime = System.currentTimeMillis();
// 
//        thread3.start();
//        thread4.start();
//        try {
//            thread3.join();
//            thread4.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("multiThreadCounterLock Time taken: " + (endTime - startTime) + " ms");
//        System.out.println("multiThreadCounterLock Value is: " + multiThreadCounterLock.getCount());
    

//	
        CounterMultiThreadAtomic counterMultiThreadAtomic1 = new CounterMultiThreadAtomic();
        CounterMultiThreadAtomic counterMultiThreadAtomic2 = new CounterMultiThreadAtomic();
        startTime = System.currentTimeMillis();
        Thread thread03 = new Thread(counterMultiThreadAtomic1);
        Thread thread04 = new Thread(counterMultiThreadAtomic2);
        thread03.start();
        thread04.start();
        try{
        	thread03.join();
        	thread04.join();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("counterMultiThreadAtomic Time taken: " + (endTime - startTime) + " ms");
        System.out.println("counterMultiThreadAtomic Value is: " + counterMultiThreadAtomic1.getCount());
        
	}
    
}
