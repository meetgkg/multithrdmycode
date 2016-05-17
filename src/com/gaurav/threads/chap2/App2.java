package com.gaurav.threads.chap2;

import java.util.Scanner;

class Processor extends Thread{
	
	//to avoid caching by any particular thread
	private volatile boolean running = true;
	
	public void run(){
		while(running){
			System.out.println("Hello");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown(){
		running = false;
	}
}

public class App2 {

	public static void main(String[] args) {
		Processor p = new Processor();
		p.start();
		
		System.out.println("Press Return to stop ....");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		p.shutdown();
		scan.close();
	}

}
