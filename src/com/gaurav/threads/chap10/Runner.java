package com.gaurav.threads.chap10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	
	//Instead of synchronized we are using ReentrantLock
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment(){
		for(int i = 0 ; i < 10000 ; i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();
		
		System.out.println("Waiting...");
		cond.await();
		System.out.println("Woken Up..");
		
		increment();
		/*
		 * every time you lock it, you also need to unlock it
		 * In case of exceptions put it in finally block
		 */
		lock.unlock();
	}
	
	public void secondThread() throws InterruptedException{
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press Enter Key....");
		new Scanner(System.in).nextLine();
		System.out.println("Got Enter Key");
		
		cond.signal();
		
		increment();
		lock.unlock();
	}
	
	public void finished(){
		System.out.println("Count is : "+count);
	}
}
