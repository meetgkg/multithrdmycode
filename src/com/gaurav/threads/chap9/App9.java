package com.gaurav.threads.chap9;

public class App9 {
	
	public static void main(String[] args) throws InterruptedException {
		
		final Producer producer = new Producer();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();

	}

}
