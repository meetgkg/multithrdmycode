package com.gaurav.threads.chap3;

public class App3 {

	private int count = 0;
	
	private synchronized void increment(){
		count++;
	}
	
	public static void main(String[] args) {
		App3 app = new App3();
		app.doWork();
	}

	private void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++){
					increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++){
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			/*
			 * https://docs.oracle.com/javase/tutorial/essential/concurrency/join.html
			 * The join method allows one thread to wait for the completion of another. 
			 * If t is a Thread object whose thread is currently executing,
			 * t.join() => causes the current thread to pause execution until t's thread terminates
			 */
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Count is : "+ count);
	}

}
