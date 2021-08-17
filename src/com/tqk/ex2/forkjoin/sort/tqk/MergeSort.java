package com.tqk.ex2.forkjoin.sort.tqk;


import java.util.Arrays;

public class MergeSort {
    public final static int MAX = 20;

    public static int[] sort(int[] array){
        if(array.length<=MAX){
            int[] left= MaoPaoSort.sort(array);
            return  left;
        }else{
            /*切分数组，然后递归调用*/
            int mid = array.length / 2;
            int[] left= Arrays.copyOfRange(array,0,mid);
            int[] right=Arrays.copyOfRange(array,mid,array.length);
            return merge(sort(left),sort(right));
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
        int[] array= sort(MakeArray.makeArray(30));
        for (int i :array){
            System.out.print(i+",");
        }
        System.out.println("");
        System.out.println(" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
