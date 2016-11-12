/**
 * 
 */
package edu.sevenvoid.jvm.test.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * CLHLock自旋锁的Java实现,适用于SMP的处理器架构
 * 
 * @author sevenvoid
 *
 * 2016年11月8日
 */
public class CLHLock implements Lock{
	
	private final AtomicReference<LNode> tail = new AtomicReference<LNode>(new LNode());
	
	private  ThreadLocal<LNode> myPred;
	
	private  ThreadLocal<LNode> myNode;
	
	public CLHLock() {
		myNode = new ThreadLocal<LNode>() {
			protected LNode initialValue() {
				return new LNode();
			}
		};
		
		myPred = new ThreadLocal<LNode>() {
			protected LNode initialValue() {
				return new LNode();
			}
		};
	}

	@Override
	public void lock() {
		LNode node = myNode.get();   //线程本地变量，不存在时，将会去创建一个节点
		node.setLocked(true);      //设置自己的状态为locked=true表示需要获取锁
		LNode pred = tail.getAndSet(node);  
		myPred.set(pred);
		while(pred.isLocked()) {  //当前线程在前驱节点的locked字段上旋转，知道前驱节点释放锁资源
		}
		System.out.print(Thread.currentThread().getName() + " : ");
	}
	
	@Override
	public void unlock() {
		myNode.get().setLocked(false);   //释放锁操作时将自己的locked设置为false，可以使得自己的后继节点可以结束自旋
		myNode.set(myPred.get());   //回收自己这个节点，从虚拟队列中删除
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg2) throws InterruptedException {
		return false;
	}


}
