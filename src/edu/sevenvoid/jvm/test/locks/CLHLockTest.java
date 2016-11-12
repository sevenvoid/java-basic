/**
 * 
 */
package edu.sevenvoid.jvm.test.locks;

/**
 * @author sevenvoid
 *
 * 2016年11月8日
 */
public class CLHLockTest {
	
	private int value = 0;
	
	private CLHLock clhLocks = new CLHLock();
	
	public void inCrement() {
		clhLocks.lock();
		if(value >= 200) {
			System.out.println("The Value is : " + value);
			clhLocks.unlock();
			return ;
		}
		value ++ ;
		System.out.println("The Value is : " + value);
		clhLocks.unlock();
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
		CLHLockTest clhtest = new CLHLockTest();
		for(int i=0; i < 3; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while(clhtest.getValues() < 200) {
						clhtest.inCrement();
//						clhtest.inCreamentNoLock();
					}
					System.out.println(Thread.currentThread().getName() + " is Over");
				}
			});
			thread.start();
		}
	}

}
