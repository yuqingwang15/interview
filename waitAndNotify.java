/**
 * multi-thread
 * @Author   WYQ
 * @DateTime 2017-10-21T14:17:44+0800
 * @param    args                     [description]
 */


public class waitAndNotify{
	public void produce() throws InterruptedException{
		synchronized(this){
			System.out.println("produce");
			wait();
			System.out.println("Resumed");

		}
		
	}
	public void consume() throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized(this){
			System.out.println("consume");
			sc.nextLine();
			System.out.println("returnkey pressed");
			notify();
			Thread.sleep(4000);

		}
	}	

	public static void main(String[] args){

	}

}
