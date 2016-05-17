package com.gaurav.threads.chap6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
	private CountDownLatch latch;
	public Processor(CountDownLatch latch) {
		super();
		this.latch = latch;
	}
	@Override
	public void run() {
		System.out.println("Processor Started !!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}
	
}

public class App6 {

	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService exec = Executors.newFixedThreadPool(3);
		
		for(int i =0; i < 3; i++){
			exec.submit(new Processor(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed !!!");
	}

}
