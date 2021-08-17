package com.tqk.ex2.forkjoin.sum;


import cn.tqk.tools.SleepTools;
import com.tqk.ex2.forkjoin.sort.tqk.MakeArray;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author tqk
 */
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer>{
        // 阈值
        private final static int THRESHOLD =20;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            int count=0;
            if(THRESHOLD<(toIndex-fromIndex)){
                for (int i=fromIndex;i<=toIndex;i++){
                    SleepTools.ms(1);
                    count+=src[i];
                }
                return count;
            }else{
                int mid=src.length/2;
                int[] left=Arrays.copyOfRange(src,fromIndex,mid);
                int[] right=Arrays.copyOfRange(src,mid,toIndex);
                SumTask leftSumTask=new SumTask(left,0,left.length);
                SumTask rightSumTask=new SumTask(right,0,right.length);
                invokeAll(leftSumTask,rightSumTask);
                return  leftSumTask.join()+rightSumTask.join();
            }
        }

        public static void main(String[] args) {
            int[] src = MakeArray.makeArray(100000);
            /*new出池的实例*/
            ForkJoinPool pool = new ForkJoinPool();
            /*new出Task的实例*/
            SumTask innerFind = new SumTask(src,0,src.length-1);

            long start = System.currentTimeMillis();

            pool.invoke(innerFind);
            //System.out.println("Task is Running.....");

            System.out.println("The count is "+innerFind.join()
                    +"\n spend time:"+(System.currentTimeMillis()-start)+"ms");
        }
    }
}
