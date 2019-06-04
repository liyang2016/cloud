package com.leon.cloud.common.uilts;

public class ArraysUtils {
	public static void printArrays(int[] arrays){
		for(int i=0;i<arrays.length;i++){
			System.out.print(arrays[i]+"  ");
		}
		System.out.println();
	}
	
	public static void exchangeElement(int[] array,int i,int j){
		int temp;
		if(i<array.length&&j<array.length){
			temp=array[i];
			array[i]=array[j];
			array[j]=temp;
		}
	}
}
