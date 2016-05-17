package com.gaurav.threads.chap1;


class RunnerR implements Runnable{
	@Override
	public void run() {
		for(int i = 0; i < 10 ; i++){
			System.out.println("Hello "+i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class AppRunn {
	public static void main(String[] args) {
		Thread t1 = new Thread(new RunnerR());
		t1.start();
		Thread t2 = new Thread(new RunnerR());
		t2.start();
	}
}
