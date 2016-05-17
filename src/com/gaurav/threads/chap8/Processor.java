package com.gaurav.threads.chap8;

import java.util.Scanner;

public class Processor {
	
	public void producer() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer Thread ...");
			//wait must be in synchronized block
			//to regarin control, notify shud invoke. notify must be locked in same lock as wait
			wait();
			System.out.println("Producer Resumed ...");
		}
	}
	
	public void consumer() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Waiting for Return Key...");
			scanner.nextLine();
			System.out.println("Return Key Pressed ...");
			notify();//can only be in synchronized block
			Thread.sleep(6000);
		}
		scanner.close();
	}

}
