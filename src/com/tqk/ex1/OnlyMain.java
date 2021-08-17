package com.tqk.ex1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 *类说明：只有一个main方法的程序
 */
public class OnlyMain {
    public static void main(String[] args) {
        //Java 虚拟机线程系统的管理接口,通过此类可以获取线程信息数组。
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息
        // 管理工厂类，可以获取线程管理类对象ThreadMXBean
        ThreadInfo[] threadInfos =
                threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] "
                    + threadInfo.getThreadName());
        }
        /**
         * 从打印结果可以看出，虽然面上只看到main线程，但是实际上Java程序中启动了很多线程。
         *         [6] Monitor Ctrl-Break //监控 Ctrl-Break 中断信号的
         *          [5] Attach Listener //内存 dump，线程 dump，类信息统计，获取系统属性等
         *          [4] Signal Dispatcher // 分发处理发送给 JVM 信号的线程
         *          [3] Finalizer // 调用对象 finalize 方法的线程
         *          [2] Reference Handler//清除 Reference 的线程
         *          [1] main //main 线程，用户程序入口
         */
    }
}