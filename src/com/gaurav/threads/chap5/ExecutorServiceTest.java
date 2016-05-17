package com.gaurav.threads.chap5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	
	private int id;
	
	public Processor(int id){
		this.id = id;
	}
	
	public void run(){
		System.out.println("Starting ID : "+ id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished ID : "+ id);
	}
}


public class ExecutorServiceTest {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(2);//Will create a Thread pool of 2 Threads
		
		for(int i = 0; i < 5; i ++){
			exec.submit(new Processor(i));
		}
		
		exec.shutdown();
		
		System.out.println("All Tasks Submitted !!");
		
		try {
			exec.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All Tasks Completed !!");
	}

}
