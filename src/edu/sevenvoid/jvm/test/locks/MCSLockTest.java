/**
 * 
 */
package edu.sevenvoid.jvm.test.locks;

/**
 * @author sevenvoid
 *
 * 2016年11月9日
 */

public class MCSLockTest {
	
	private int value = 0;
	
	private MCSLock mcsLock = new MCSLock();
	
	public void inCrement() {
		mcsLock.lock();
		if(value >= 200) {
			System.out.println("The Value is : " + value);
			mcsLock.unlock();
			return ;
		}
		value ++ ;
		System.out.println("The Value is : " + value);
		mcsLock.unlock();
	}
	
	public void inCreamentNoLock() {
		if(value >= 200) {
			System.out.println("The Value is : " + value);
			return ;
		}
		value ++ ;
		System.out.println("The Value is : " + value);
	}
	
	public int getValues() {
		return value;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MCSLockTest mcsTest = new MCSLockTest();
		for(int i=0; i < 5; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while(mcsTest.getValues() < 200) {
//						mcsTest.inCrement();
						mcsTest.inCreamentNoLock();
					}
					System.out.println(Thread.currentThread().getName() + " is Over");
				}
			});
			thread.start();
		}
	}
}
