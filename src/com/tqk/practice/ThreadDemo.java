package com.tqk.practice;

public class ThreadDemo {
    public static void main(String[] args) {
        Clerk clerk=new Clerk(20);
        Producer p1=new Producer(clerk);
        Customer c1=new Customer(clerk);
        Customer c2=new Customer(clerk);

        p1.setName("生产者1");
        c1.setName("消费者1");
        c2.setName("消费者2");

        p1.start();
        c1.start();
        c2.start();
    }
}
class Clerk{
    private int productCount=0;

    public Clerk(int productCount) {
        this.productCount = productCount;
    }

    //生产产品
    public synchronized void produceProduct() {//同步监视器为clerk
        if(productCount<20){//如果不进行同步操作，比如进入if还没有输出时的productCount为10，但阻塞了，下面执行了--操作，再进行输出就是9了
            productCount++;//放到这里不放到输出下面的原因是，防止输出开始生产第0个产品
            System.out.println(Thread.currentThread().getName()+"开始生产第"+productCount+"个产品");
            notify();//如果消费者线程先执行，那么此时因为没有产品不能消费，就执行else就wait了，对于生产者来说，只要生产了一个产品就可以去唤醒消费者了
        }else{
            //不用生产了
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //消费产品
    public synchronized void consumeProduct() {//同步监视器也是clerk，当进去以后，另一个就进不去了，因为同步监视器只有一个
        if(productCount>0){
            System.out.println(Thread.currentThread().getName()+"开始消费第"+productCount+"个产品");
            productCount--;//当生产者生产到产品为20时，不能再生产了，就wait了，消费者消费一个产品后又可以把生产者唤醒
            notify();
        }else {
            //不能消费了
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer extends Thread{//生产者
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+"：开始生产产品……");
        while(true){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}
class Customer extends Thread{//消费者
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+"：开始消费产品……");
        while(true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}
