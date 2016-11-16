/**
 * 
 */
package edu.sevenvoid.jvm.lock;

/**
 * @author sevenvoid
 *
 * 2016年11月8日
 */
public class LNode {
	
	private volatile boolean locked = false;
	
	private LNode next = null;
	
	public LNode getNext() {
		return next;
	}
	
	public void setNext(LNode node) {
		this.next = node;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
