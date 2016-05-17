package com.gaurav.threads.chap12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App12 {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i=0; i< 200; i++){
			exec.submit(new Runnable() {
				@Override
				public void run() {
					Connection.getConnection().connect();
				}
			});
		}
		
		exec.shutdown();
		exec.awaitTermination(1, TimeUnit.DAYS);

	}

}
