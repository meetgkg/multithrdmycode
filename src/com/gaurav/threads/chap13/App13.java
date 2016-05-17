package com.gaurav.threads.chap13;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App13 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		/*
		 * http://stackoverflow.com/questions/141284/the-difference-between-the-runnable-and-callable-interfaces-in-java
		 * The Callable interface is similar to Runnable, in that both are designed for classes whose instances are potentially 
		 * executed by another thread. A Runnable, however, does not return a result and cannot throw a checked exception
		 */
		Future<Integer> future = exec.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int duration = new Random().nextInt(4000);
				System.out.println("Starting..");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Finished!!");
				return duration;
			}
			
		});
		
		exec.shutdown();
		try {
			exec.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		exec.submit(new Runnable() {
			@Override
			public void run() {
				
				System.out.println("Starting..");
				
				try {
					Thread.sleep(new Random().nextInt(4000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Finished!!");
			}
		});
		*/
	}

}
