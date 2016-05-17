package com.gaurav.threads.chap9;

import java.util.LinkedList;
import java.util.Random;

public class Producer {
	
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock1 = new Object();
	
	public void producer() throws InterruptedException{
		int value = 0;
		while(true){
			synchronized (lock1) {
				while(list.size() == LIMIT){
					lock1.wait();
				}
				list.add(value++);
				lock1.notify();
			}
		}
	}
	
	public void consumer() throws InterruptedException{
		
		Random random = new Random();
		
		while(true){
			synchronized (lock1) {
				
				while(list.size() == 0){
					lock1.wait();
				}
				
				System.out.println("List Size : "+list.size());
				int value = list.removeFirst();
				System.out.println("Value is : "+value);
				lock1.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}

}
