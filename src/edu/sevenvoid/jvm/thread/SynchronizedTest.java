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
	
	public synchronized long inCrementWithSynchronizedMethod() {
		i++;
		System.out.println("The thread name : " + Thread.currentThread().getName() + "  i = " + i);
		return i;
	}
	
	@Override
	public void run() {
		for(int i=0; i<2; i++) {
			inCrementWithSynchronizedMethod();
		}
	}
	
	public static void main(String arg[]) {
		SynchronizedTest synchronizedTest1 = new SynchronizedTest();
		SynchronizedTest synchronizedTest2 = new SynchronizedTest();
		Thread thread1 = new Thread(synchronizedTest1);
		Thread thread2 = new Thread(synchronizedTest2);
		thread1.start();
		thread2.start();
	}
}
