/**
 * multi-thread
 * @Author   WYQ
 * @DateTime 2017-10-21T14:17:44+0800
 * @param    args                     [description]
 */


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class countdownTest{
	static class Processor implements Runnable{
		private CountDownLatch latch;

		public  Processor(CountDownLatch latch){
			this.latch = latch;
		}
	
		public void run(){
			System.out.println("started");

			try{ Thread.sleep(1000);}catch (InterruptedException e) {}

			latch.countDown();
	}
}
	public static void main(String[] args){
		CountDownLatch latch = new CountDownLatch(6);
		ExecutorService exe = Executors.newFixedThreadPool(4);
		for(int i = 0 ;i<5;i++){
			exe.submit(new Processor(latch));
			System.out.println("thread" + i);
		}

		try{latch.await();} catch(InterruptedException e) {}

		System.out.println("com");
	}
}
