package com.gaurav.threads.chap11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private void acquireLock(Lock firstLock, Lock secondLock) throws InterruptedException{
		while(true){
			//Acquire Lock
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}finally{
				if(gotFirstLock && gotSecondLock){
					return;
				}
				if(gotFirstLock){
					firstLock.unlock();
				}
				if(gotSecondLock){
					secondLock.unlock();
				}
			}
			//Lock not acquired
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException{
		for(int i=0; i< 10000; i++){
			acquireLock(lock1, lock2);
			Account.transfer(acc1, acc2, new Random().nextInt(100));
			lock1.unlock();
			lock2.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException{
		for(int i=0; i< 10000; i++){
			acquireLock(lock2, lock1);
			Account.transfer(acc2, acc1, new Random().nextInt(100));
			lock2.unlock();
			lock1.unlock();
		}
	}
	
	public void finished(){
		System.out.println("Account 1 Balance : "+acc1.getBalance());
		System.out.println("Account 2 Balance : "+acc2.getBalance());
		System.out.println("Total Balance : "+ (acc1.getBalance() + acc2.getBalance()));
	}
}
