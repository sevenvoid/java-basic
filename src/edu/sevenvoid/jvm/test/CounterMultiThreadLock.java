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
public class CounterMultiThreadLock implements Runnable{

	long count;

	public CounterMultiThreadLock(long count) {
		this.count = count;
	}

//	synchronized void incrementByOne() {
//		count++;
//	}
//	
	void incrementByOne() {
		count++;
	}

	long getCount() {
		return count;
	}

	@Override
	public void run() {
		Lock lock = new ReentrantLock();
		for (long index = 0; index<1000000000L; index++) {
			try{
				lock.lock();
				incrementByOne();
			}finally {
				lock.unlock();
			}
        }
	}
}
