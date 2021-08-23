package com.tqk.ex3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：演示基本类型的原子操作类
 */
public class UseAtomicInt {
    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        //后++
        System.out.println(ai.getAndIncrement());
        //前++
        System.out.println(ai.incrementAndGet());
        //ai.compareAndSet();
        System.out.println(ai.addAndGet(24));
        System.out.println(ai.getAndSet(99));
        System.out.println(ai);
    }
}