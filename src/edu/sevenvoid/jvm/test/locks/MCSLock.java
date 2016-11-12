/**
 * 
 */
package edu.sevenvoid.jvm.test.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * MCSLock自旋锁的Java实现,适用于NUMA的处理器架构
 * 
 * @author sevenvoid
 *
 * 2016年11月9日
 */
public class MCSLock implements Lock{
	
	private AtomicReference<LNode> tail;
	
	ThreadLocal<LNode> myNode;
	
	public MCSLock() {
		tail = new AtomicReference<LNode>(null);
		myNode = new ThreadLocal<LNode>() {
			@Override
			protected LNode initialValue() {
				return new LNode();
			}
		};
	}
	
	@Override
	public void lock() {
		LNode node = myNode.get();
		LNode prev = tail.getAndSet(node);
		if(prev != null) {
			node.setLocked(true);
			prev.setNext(node);
			while(node.isLocked());
		}
		System.out.print(Thread.currentThread().getName() + " : ");
	}
	
	@Override
	public void unlock() {
		LNode node = myNode.get();
		LNode next = node.getNext();
		if(next == null) {
			if(tail.compareAndSet(node, null)) 
				return;
			while(next == null);   //更新tail节点失败说明正在有另一个线程在获取锁资源，并构建阻塞队列
								   //所以必须等到next节点构建完成(也即另一个线程的Lock操作完成构建出一个完整的队列)，自己才能释放掉
		} 
		next.setLocked(false);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}


}
