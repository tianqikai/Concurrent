package com.tqk.ex4.aqs;

import cn.tqk.tools.SleepTools;

import java.util.concurrent.locks.Lock;

public class TestMyLock {
    private  volatile  int sum=0;
    public void test() {
        final Lock lock = new SelfLock();
        class Worker extends Thread {

            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName());
                    sum++;
                    SleepTools.second(1);
                    System.out.println(sum);
                } finally {
                    lock.unlock();
                }
            }
        }
        // 启动4个子线程
        for (int i = 0; i < 4; i++) {
            Worker w = new Worker();
            //w.setDaemon(true);
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
        	SleepTools.second(1);
            //System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }
}
