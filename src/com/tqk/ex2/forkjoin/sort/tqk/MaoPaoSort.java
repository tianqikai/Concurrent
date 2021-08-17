package com.tqk.ex2.forkjoin.sort.tqk;

import com.tqk.ex2.forkjoin.sort.InsertionSort;

public class MaoPaoSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int  currentValue;
        int num;
        for (int i = 0; i <=array.length - 1; i++) {
            currentValue= array[i];
            for (int j=i+1;j<=array.length-1;j++){
                //找出一个最小值
                if(currentValue>array[j]){
                    num=currentValue;
                    currentValue=array[j];
                    array[j]=num;
                }else{
                    continue;
                }
            }
            array[i]=currentValue;
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println("============================================");
        int[] darray=InsertionSort.sort(MakeArray.makeArray(15));
        for (int i: darray) {
            System.out.print(i+", ");
        }
    }
}
