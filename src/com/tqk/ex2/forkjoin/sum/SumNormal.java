package com.tqk.ex2.forkjoin.sum;

import cn.tqk.tools.SleepTools;
import com.tqk.ex2.forkjoin.sort.tqk.MakeArray;

/**
 * 单线程执行累加
 * The count is 149680774
 *  spend time:10058ms
 *
 The count is 149556884
 spend time:10028ms
 */
public class SumNormal {
	
	public static void main(String[] args) {
	    int count = 0;
	    int[] src = MakeArray.makeArray(100000);

	    long start = System.currentTimeMillis();
	    for(int i= 0;i<src.length;i++){
			SleepTools.ms(1);
	    	count = count + src[i];
	    }
	    System.out.println("The count is "+count
	            +"\n spend time:"+(System.currentTimeMillis()-start)+"ms");
	}

}