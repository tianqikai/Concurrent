package com.tqk.ex4;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) {
        Thread a = new Thread(()->{
            System.out.println("进入到a线程");
            LockSupport.park();
            System.out.println("a线程被唤醒");
        },"线程a");
        a.start();
        new Thread(()->{
            System.out.println("进入到b线程");
            LockSupport.unpark(a);
        },"线程b") .start();
    }
}
