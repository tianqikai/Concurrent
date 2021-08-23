package com.tqk.ex3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 */
public class HalfAtomicIntTqk {
    AtomicInteger atomicInteger=new AtomicInteger(0);
    /*请完成这个递增方法*/
    public void increament() {
        while(true){
            Integer i= atomicInteger.get();
            boolean bool=atomicInteger.compareAndSet(i,++i);
            if(bool){
               break;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        HalfAtomicIntTqk halfAtomicInt=new HalfAtomicIntTqk();
        for (int i=0;i<100;i++){
           new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<1000;i++){
                        halfAtomicInt.increament();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(halfAtomicInt.atomicInteger);
    }
}
