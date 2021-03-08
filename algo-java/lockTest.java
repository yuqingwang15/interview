/**
 * multi-thread
 * @Author   WYQ
 * @DateTime 2017-10-21T14:17:44+0800
 * @param    args                     [description]
 */

import java.util.LinkedList;


public class lockTest throws InterruptedException{
	private static BlockingQueue<Integer>  queue = new ArrayBlockingQueue<Integer>(4);
 	public static void producer() throws InterruptedException{
    	int value = 0;
		while(true){
			synchronized(lock){
			while(list.size()==limit){
				lock.wait();
			}

			list.add(value++);
			lock.notify();
			}
		}

    }

    public static void consumer() throws InterruptedException{
    	while(true){
			synchronized(lock){
			while(list.size()==0){
				lock.wait();
			}
			int value = list.removeFirst();
			System.out.println("value" + value);
			lock.notify();
		}
    }
	
	public static void main(String[] args) throws InterruptedException{
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try{System.out.println("t1 started"); 
					producer();}catch(InterruptedException e){}
			}
		});

		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try{ System.out.println("t2 started");
					consumer();}catch(InterruptedException e){}
			}

		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();


	}
}

