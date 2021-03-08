/**
 * multi-thread
 * @Author   WYQ
 * @DateTime 2017-10-21T14:17:44+0800
 * @param    args                     [description]
 */



import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Random;



public class BlockingQueueTest{
	private static BlockingQueue<Integer>  queue = new ArrayBlockingQueue<Integer>(4);
 	public static void producer() throws InterruptedException{
    	Random random = new Random();
    	while(true){
    		queue.put(random.nextInt(4));
    		System.out.println(queue);
    	}
    }

    public static void consumer() throws InterruptedException{
    	Random random = new Random();
    	Thread.sleep(100);
    	while(true){
    	
    		// if(random.nextInt(10) ==0){
    		// 	Integer value = queue.take();
    		// 	System.out.println("taken "+value+" size "+queue.size());
    		// }
    		if(queue.size()==4){
    			Thread.sleep(2000);
    			Integer value = queue.take();
    			System.out.println("taken "+value+" size "+queue.size() + "		"+queue );

    		}
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