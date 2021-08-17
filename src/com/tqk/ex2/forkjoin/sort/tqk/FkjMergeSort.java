package com.tqk.ex2.forkjoin.sort.tqk;

import com.tqk.ex2.forkjoin.sort.FkSort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FkjMergeSort {
    private static class SumTask extends RecursiveTask<int[]> {
        private final static int THRESHOLD = 2;
        private int[] src;

        public SumTask(int[] src) {
            this.src = src;
        }

        @Override
        protected int[] compute() {
            if(src.length<=2){
                return MaoPaoSort.sort(src);
            }else {
                int mid = src.length / 2;
                int[] left= Arrays.copyOfRange(src,0,mid);
                int[] right= Arrays.copyOfRange(src,mid,src.length);
                FkjMergeSort.SumTask leftTask = new FkjMergeSort.SumTask(left);
                FkjMergeSort.SumTask rightTask = new FkjMergeSort.SumTask(right);
                invokeAll(leftTask,rightTask);
                int[] leftResult=leftTask.join();
                int[] rightResult=rightTask.join();
                return merge(leftResult,rightResult);
            }
        }
    }
    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for(int i=0,j=0,x=0;i<result.length;i++){
            if (j >= left.length) {
                // 左边数组已经取完，完全取右边数组的值即可
                result[i] = right[x++];
            } else if (x >= right.length){
                // 右边数组已经取完，完全取左边数组的值即可
                result[i] = left[j++];
            }else if(left[j]>right[x]){
                result[i]=right[x++];
            }else{
                result[i]=left[j++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("============================================");
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray(30);

        FkjMergeSort.SumTask innerFind = new FkjMergeSort.SumTask(src);

        int[] array1=pool.invoke(innerFind);
        int[] array2=innerFind.join();
        for (int i :array1){
            System.out.print(i+",");
        }
        System.out.println("");
        for (int i :array2){
            System.out.print(i+",");
        }
        System.out.println(" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
