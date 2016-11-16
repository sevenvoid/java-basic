/**
 * 
 */
package edu.sevenvoid.jvm.thread;

/**
 * @author Loops
 *
 */
public class CounterLock {

	long count;

	public CounterLock(long count) {
		this.count = count;
	}

	void incrementByOne() {
		count++;
	}

	long getCount() {
		return count;
	}
}
