/**
 * 
 */
package edu.sevenvoid.jvm.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Loops
 *
 */
public class CounterMultiThreadAtomic implements Runnable{

	private AtomicLong count;

	public CounterMultiThreadAtomic() {
		count = new AtomicLong(0);
	}

	void incrementByOne() {
		count.incrementAndGet();
	}

	long getCount() {
		return count.get();
	}

	@Override
	public void run() {
		for (long index = 0; index<1000000000L; index++) {
			incrementByOne();
        }
	}
}
