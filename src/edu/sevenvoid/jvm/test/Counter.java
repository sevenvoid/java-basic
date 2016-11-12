/**
 * 
 */
package edu.sevenvoid.jvm.test;

/**
 * @author Loops
 *
 */
public class Counter {

	long count;

	public Counter(long count) {
		this.count = count;
	}

	void incrementByOne() {
		count++;
	}

	long getCount() {
		return count;
	}
}
