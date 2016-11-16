/**
 * 
 */
package edu.sevenvoid.jvm.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Loops
 *
 */
public class CounterAtomic {
	private AtomicLong count;

	public CounterAtomic() {
		count = new AtomicLong(0);
	}

	void incrementByOne() {
		count.incrementAndGet();
	}

	long getCount() {
		return count.get();
	}
}
