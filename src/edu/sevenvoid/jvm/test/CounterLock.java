/**
 * 
 */
package edu.sevenvoid.jvm.test;

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
