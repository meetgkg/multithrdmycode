package com.gaurav.threads.chap14;

import java.util.Random;

public class App14 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				double duration = new Random().nextDouble();
				for(int i=0; i < 1E8; i++){
					
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interrupted");
						break;
					}
					
					Math.sin(duration);
				}
			}
		});
		t1.start();
		
		Thread.sleep(500);
		t1.interrupt();
		
		t1.join();
		System.out.println("Finished...");
	}

}
