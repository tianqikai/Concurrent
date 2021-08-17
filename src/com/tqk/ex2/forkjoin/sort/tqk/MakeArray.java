package com.tqk.ex2.forkjoin.sort.tqk;

import java.util.Random;

/**
 * @author Administrator
 */
public class MakeArray {
    public static int[] makeArray(int ARRAY_LENGTH) {

        //new一个随机数发生器
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for(int i=0;i<ARRAY_LENGTH;i++){
            //用随机数填充数组
            result[i] =  r.nextInt(ARRAY_LENGTH*3);
        }
//        for (int i:result
//             ) {
//            System.out.print(i+",");
//        }
        System.out.println();
        return result;

    }
}