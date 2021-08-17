package com.tqk.ex1.safeend;

/**
 *类说明：如何安全中断线程
 */
public class EndThread {
	
	private static class UseThread extends Thread{

		public UseThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName+" interrrupt STRAT flag ="+isInterrupted());
//			while(!Thread.currentThread().isInterrupted()){
			while(!isInterrupted()){
				/*Thread.interrupted()判断之后，会将true再改为false*/
//			while(!Thread.interrupted()){
//			/*endThread.interrupt()将标志位置为true后，业务线程不做判断的话线程还会继续执行（协助式，不是强制式）*/
//			while(true){
				System.out.println(threadName+" is running");
				System.out.println(threadName+"inner interrrupt RUNNING flag ="
						+isInterrupted());
			}
			System.out.println(threadName+" interrrupt END flag ="+isInterrupted());
			System.out.println(threadName+" interrrupt END flag ="+Thread.interrupted());
			System.out.println(threadName+" interrrupt END flag ="+isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread endThread = new UseThread("endThread");
		endThread.start();
		Thread.sleep(20);
		endThread.interrupt();//中断线程，其实设置线程的标识位true
	}

}
