package com.gaurav.threads.chap12;

import java.util.concurrent.Semaphore;

public class Connection {
	
	private static Connection instance = new Connection();
	private int connections = 0; 
	private Semaphore semaphore = new Semaphore(10, true);
	
	private Connection(){
	}
	
	public static Connection getConnection(){
		return instance;
	}
	
	public void connect(){
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			doConnect();
		}finally{
			semaphore.release();
		}
	}
	
	public void doConnect(){
		
		synchronized (this) {
			connections++;
			System.out.println("Current Connetions : "+connections);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			connections--;
		}
		
	}
	
}
