/**
 * multi-thread
 * @Author   WYQ
 * @DateTime 2017-10-21T14:17:44+0800
 * @param    args                     [description]
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


import java.util.Random;


public class lockTest2 {
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private static class Account{
		private int balance = 10000;
		public int getBalance(){
			return this.balance;
		}
		public void deposit(int amount){
			balance+=amount;
		}
		public void withdraw(int amount){
			balance-=amount;
		}
		public static void transfer(Account acc1,Account acc2,int amount){
			acc1.withdraw(amount);
			acc2.deposit(amount);
		}
	}

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private void acquireLocks(Lock firstl,Lock secondl){
		while(true){
			boolean gotf = false;
			boolean gotse = false;
			try{
				gotf = firstl.tryLock();
				gotse = secondl.tryLock();

			}
			finally{
				if(gotf && gotse){
					System.out.println(" gotf&se ");
					return;
				}
				if(gotf){
					System.out.println(" gotf ");
					firstl.unlock();

				}
				if(gotse){
					System.out.println(" gotse ");
					secondl.unlock();
				}
			}

			try{Thread.sleep(1);}catch(InterruptedException e){}
			
		}

	}


	public void first() throws InterruptedException{
		Random random = new Random();
		for(int i =0;i<1;i++){
		// lock1.lock();
		// lock2.lock();
		acquireLocks(lock1,lock2);;
		try{
			Account.transfer(acc1,acc2,random.nextInt(10));
			System.out.println("first acc1 "+ acc1.getBalance()+" ,acc2 "+acc2.getBalance());

		}

		
		finally{
			lock1.unlock();
			lock2.unlock();
		}
}
	}

	public void second() throws InterruptedException{
		Random random = new Random();
		for(int i =0;i<1;i++){
		// lock1.lock();
		// lock2.lock();
		acquireLocks(lock1,lock2);;
		
		try{
			Account.transfer(acc2,acc1,random.nextInt(10));
			System.out.println("second acc1 "+ acc1.getBalance()+" ,acc2 "+acc2.getBalance());
		}

		
		finally{
			lock1.unlock();
			lock2.unlock();
		}
	}
}

	public  void finished(){
		System.out.println("acc1"+acc1.getBalance());
		System.out.println("acc2"+acc2.getBalance());
		System.out.println("total"+(acc1.getBalance()+acc2.getBalance()));
	}
	public static void main(String[] args) throws InterruptedException{
		lockTest2 dl = new lockTest2();
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try{System.out.println("t1 started"); 
					dl.second();}catch(InterruptedException e){}
			}
		});

		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try{ System.out.println("t2 started");
					dl.first();}catch(InterruptedException e){}
			}

		});
		Thread t3 = new Thread(new Runnable(){
			public void run(){
				try{System.out.println("t3 started"); 
					dl.second();}catch(InterruptedException e){}
			}
		});

		Thread t4 = new Thread(new Runnable(){
			public void run(){
				try{ System.out.println("t4 started");
					dl.first();}catch(InterruptedException e){}
			}

		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	

	
		Thread.sleep(2000);
		dl.finished();
	
	 }

}
