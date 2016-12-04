/**
 * 
 */
package edu.sevenvoid.jvm.thread;

/**
 * @author sevenvoid
 *
 * 2016年12月4日
 */
public enum SingletonTest {
	
	INSTANCE;
	
	private SingletonTest() {
		System.out.println("The current thread is : " + Thread.currentThread().getName());
	}
	public void doOtherSomething() {
		System.out.println("Hello thread : " + Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		
		for(int i=0; i<=5; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					SingletonTest.INSTANCE.doOtherSomething();
				}
			});
			thread.start();
		}

	}

}
