package com.tqk.practice;


/**
 * @author Administrator
 */
public class Practice1 {
    private    int ticket=0;
    public Practice1() {
    }
    public Practice1(int ticket) {
        this.ticket = ticket;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
    public  synchronized void productTicket(){
        if(ticket<20){
            ticket++;
            System.out.println(Thread.currentThread().getName()+"生产后："+ticket+"张票");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumerTicket(){
        if(ticket>0){
            ticket--;
            System.out.println(Thread.currentThread().getName()+"消费后："+ticket+"张票");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Practice1 practice1=new Practice1(10);
        ProducerThread producerThread=new ProducerThread(practice1);
        ConsumerThread consumerThread=new ConsumerThread(practice1);
        ConsumerThread consumerThread1=new ConsumerThread(practice1);

        producerThread.setName("生产者1");
        consumerThread.setName("消费者1");
        consumerThread1.setName("消费者2");

        producerThread.start();
        consumerThread.start();
        consumerThread1.start();
        Thread.sleep(100);
    }
}

class ProducerThread extends Thread {
    Practice1 practice1;

    public ProducerThread(Practice1 practice1) {
        this.practice1 = practice1;
    }

    @Override
    public void run() {
        System.out.println(getName()+"：开始生产产品……");
        while(true){
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            practice1.productTicket();
        }
    }
}
class ConsumerThread extends Thread {
    Practice1 practice1;

    public ConsumerThread(Practice1 practice1) {
        this.practice1 = practice1;
    }

    @Override
    public void run() {
        System.out.println(getName()+"：开始消费产品……");
        while(true){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            practice1.consumerTicket();
        }
    }
}
