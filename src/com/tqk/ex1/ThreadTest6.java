package com.tqk.ex1;

import java.util.Scanner;

public class ThreadTest6 {
    public static void main(String[] args) {
        System.out.println("请输入今天的总票数：");
        Scanner sc = new Scanner(System.in);
        int ticket = sc.nextInt();
        System.out.println("开始售票！");
        MyThread6 myThread6=new MyThread6();
        myThread6.setTicket(ticket);
        Thread thread1=new Thread(myThread6);
        Thread thread2=new Thread(myThread6);
        thread1.start();
        thread2.start();
    }
}
class MyThread6 implements Runnable{
    private   int ticket;

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public   void run() {
        while(true){
            synchronized(this){// 主方法中只new了一个对象，所以锁this是可以的，锁的就是myThread4这个对象
                this.notify();
                if (this.ticket>0){
                    System.out.println(Thread.currentThread().getName()+" |卖出去的票号："+this.ticket);
                    this.ticket--;
                    if(this.ticket>0){
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if(this.ticket<=0){
                break;
            }
        }
    }
}