package com.tqk.ex4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class SynchronizedDemo {
    /**
     * 代码中m1，m2都是由synchronized锁住的。在进入m1的时候持有锁了，方法里调用m2直接进入m2了。
     * 这个时候m1的锁还没释放。因为m2也是这个锁，所以能靠这把未释放的锁进入m2。证明了可重入性。
     *
     **/
    public synchronized void m1() {
        System.out.println("进入到m1方法！"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        m2();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {         
            e.printStackTrace();
        }       
    }
    public synchronized void m2() {
        System.out.println("进入到m2方法！"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }
    public void m3() {
        synchronized (this) {
            System.out.println("进入m3的一层同步块");
            synchronized (this) {
                System.out.println("进入m3的二层同步块");
                synchronized (this) {
                    System.out.println("进入m3的三层同步块");
                }
            }
        }
    }
    public static void main(String[] args) {
        SynchronizedDemo lockDemo = new SynchronizedDemo();
        lockDemo.m3();
        new Thread(()->lockDemo.m1()).start();
        new Thread(()->lockDemo.m2()).start();
    }
}