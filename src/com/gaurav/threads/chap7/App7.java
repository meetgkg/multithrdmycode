package com.gaurav.threads.chap7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App7 {
	
	//Threadsafe class, dont need to worry abt thread synchronization
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
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
	
	private static void producer() throws InterruptedException{
		Random random = new Random();
		while(true){
			int producerRandomValue = random.nextInt(100);
			System.out.println("Producer Random Value : "+producerRandomValue);
			queue.put(producerRandomValue);
		}
	}
	
	private static void consumer() throws InterruptedException{
		Random random = new Random();
		while(true){
			Thread.sleep(100);
			int consumerRandomValue = random.nextInt(10);
			System.out.println("Consumer Random Value : "+consumerRandomValue);
			if(consumerRandomValue == 0){
				Integer value = queue.take();
				System.out.println("Taken Value : "+value+" Queue size is : "+queue.size());
			}
		}
	}

}
