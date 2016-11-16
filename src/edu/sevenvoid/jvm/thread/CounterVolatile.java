/**
 * 
 */
package edu.sevenvoid.jvm.thread;

/**
 * @author Loops
 *
 */
public class CounterVolatile {
	volatile long count;
    public CounterVolatile(long count) {
        this.count = count;
    }
    void  incrementByOne() { count++; }
    long getCount() { return count; }
}
