/**
 * multi-thread
 * @Author   WYQ
 * @DateTime 2017-10-21T14:17:44+0800
 * @param    args                     [description]
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;



public class semaphoreTest{

	static class connection{
		Semaphore semap = new Semaphore(3);
		private int connections = 0;


		public void connect(){
			try{
				semap.acquire();
			}catch(InterruptedException e){}
			try{
				docon();
			}finally{
				semap.release();
			}			

		}

		public void docon(){
			synchronized(this){
				connections++;
				System.out.println("CURRENT connections  " + connections);
			}
			try{
				Thread.sleep(2000);
			}catch(InterruptedException e){ e.printStackTrace();}

			synchronized(this){
				connections--;
			}
		}
	}
	


	public static void main(String[] args) throws InterruptedException{
		Semaphore sem = new Semaphore(10);
		for(int i =0;i<10;i++){
	//	sem.acquire();	
	//	sem.release();
		System.out.println("Semaphore " + sem.availablePermits());
		sem.acquire();		
	//	sem.release();
		
	}
	System.out.println("done");

	ExecutorService exe = Executors.newCachedThreadPool();
	connection c = new connection();

	for(int i =0;i<20;i++){
		//System.out.println("this is"+ i);
		exe.submit(new Runnable(){
			public void run(){
				c.connect();
				
			}
		});
	}

	exe.shutdown();
	exe.awaitTermination(1,TimeUnit.DAYS);



}
}