package com.tqk.ex2.countdownlatch;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 *类说明：演示Exchange用法
 */
public class UseExchange {
    private static final Exchanger<Set<String>> exchange = new Exchanger<Set<String>>();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
            	Set<String> setA = new HashSet<String>();//存放数据的容器
                try {
                	/*添加数据
                	 * set.add(.....)
                	 * */
                    setA.add("A1");
                    setA.add("A2");
                	setA = exchange.exchange(setA);//交换set
                	/*处理交换后的数据*/
                    for (String str:setA
                    ) {
                        System.out.println("A:"+str);
                    }
                } catch (InterruptedException e) {
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
            	Set<String> setB = new HashSet<String>();//存放数据的容器
                try {
                	/*添加数据
                	 * set.add(.....)
                	 * set.add(.....)
                	 * */
                    setB.add("B1");
                    setB.add("B2");
                	setB = exchange.exchange(setB);//交换set
                	/*处理交换后的数据*/
                    for (String str:setB
                         ) {
                        System.out.println("B:"+str);
                    }
                } catch (InterruptedException e) {
                }
            }
        }).start();

    }
}
