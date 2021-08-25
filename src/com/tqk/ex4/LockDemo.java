package com.tqk.ex4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    Lock lock = new ReentrantLock();
    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    public void m1() {
        lock.lock();
        try {           
            System.out.println("进入m1方法"+getTime());
            m2();
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
        }finally {
            lock.unlock();
        }
    }
    public void m2() {
        lock.lock();
        try {
            System.out.println("进入m2方法"+getTime());
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        lockDemo.m1();

    }
}