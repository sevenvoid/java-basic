/**
 * 
 */
package edu.sevenvoid.jvm.thread;

/**
 * @author sevenvoid
 *
 * 2016年11月17日
 */
public class SynchronizedTest implements Runnable{
	
	private long i = 0;
	
	private static long j = 0;
	
	public synchronized long inCrementWithSynchronizedMethod() {
		i++;
		System.out.println("The thread name : " + Thread.currentThread().getName() + "  i = " + i);
		return i;
	}
	
	public long inCrementWithSynchronizedBlock(Object obj) {
		long ii = i;
		synchronized (obj) {
			i++;
			System.out.println("The thread name : " + Thread.currentThread().getName() + "  i = " + i);
			ii = i;
		}
		return ii;
	}
	
	public synchronized static long inCrementWithSynchronizedStatic() {
		j++;
		System.out.println("The thread name : " + Thread.currentThread().getName() + "  j = " + j);
		return j;
	}
	
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			inCrementWithSynchronizedStatic();
		}
	}
	
	public static void main(String arg[]) {
		SynchronizedTest synchronizedTest = new SynchronizedTest();
		
//		Thread thread1 = new Thread(synchronizedTest);
//		Thread thread2 = new Thread(synchronizedTest);
//		
//		thread1.start();
//		thread2.start();
		
//		SynchronizedTest synchronizedTest1 = new SynchronizedTest();
		
		Thread thread3 = new Thread(synchronizedTest);
		Thread thread4 = new Thread(synchronizedTest);
		
		thread3.start();
		thread4.start();
	}


}
