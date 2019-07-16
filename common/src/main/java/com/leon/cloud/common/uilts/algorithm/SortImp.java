package com.leon.cloud.common.uilts.algorithm;


import com.leon.cloud.common.uilts.ArraysUtils;

public class SortImp {

	/*
	 * 直接插入排序 升序 时间复杂度：O（n^2）
	 */
	public static void straightInsertSort(int[] arrays) {
		for (int i = 1; i < arrays.length; i++) {
			int index = i;
			int key = arrays[i];
			while (index > 0 && key < arrays[index - 1]) {
				arrays[index] = arrays[index - 1];
				index--;
			}
			arrays[index] = key;

			// PrintArrays.printArrays(arrays);
		}
	}

	/*
	 * 简单选择排序
	 */
	public static void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int minKey = i;
			int minValue = array[i];
			for (int j = i; j < array.length; j++) {
				if (array[j] < minValue) {
					minKey = j;
					minValue = array[j];
				}
			}
			array[minKey] = array[i];
			array[i] = minValue;
		}
	}

	/*
	 * 堆排序
	 */
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		buildMaxHeap(arr);
		for (int i = arr.length - 1; i > 0; i--) {
			ArraysUtils.exchangeElement(arr, i, 0);
			maxHeapify(arr, 0, i);
		}
	}

	/*
	 * 建立一个最大堆
	 */
	private static void buildMaxHeap(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		int index = arr.length / 2 - 1;
		for (int i = index; i >= 0; i--) {
			maxHeapify(arr, i, arr.length);
		}
	}

	/*
	 * 维护堆 确保堆顶对象最大
	 */
	private static void maxHeapify(int[] arr, int i, int heapSize) {
		int l = leftchild(i);
		int r = rightchild(i);
		int largest = i;
		if (l < heapSize && arr[l] > arr[largest]) {
			largest = l;
		}
		if (r < heapSize && arr[r] > arr[largest]) {
			largest = r;
		}
		if (largest != i) {
			ArraysUtils.exchangeElement(arr, i, largest);
			maxHeapify(arr, largest, heapSize);
		}
	}

	private static int leftchild(int i) {
		return 2 * i + 1;
	}

	private static int rightchild(int i) {
		return 2 * i + 2;
	}

	/*
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					ArraysUtils.exchangeElement(arr, j, j + 1);
				}
			}
		}
	}

	/*
	 * 改进后的冒泡排序
	 */
	public static void bubbleSortPlus_1(int[] arr) {
		int i = arr.length - 1;
		while (i > 0) {
			int pos = 0;
			for (int j = 0; j < i; j++)
				if (arr[j] > arr[j + 1]) {
					pos = j; // 记录交换的位置
					ArraysUtils.exchangeElement(arr, j, j + 1);
				}
			i = pos; // 为下一次排序作准备
		}
	}

	/*
	 * 改进后的冒泡排序
	 */
	public static void bubbleSortPlus_2(int[] arr) {
		int low = 0;
		int high = arr.length - 1; // 设置变量的初始值
		int j;
		while (low < high) {
			for (j = low; j < high; ++j) // 正向冒泡,找到最大者
				if (arr[j] > arr[j + 1]) {
					ArraysUtils.exchangeElement(arr, j, j + 1);
				}
			--high; // 修改high值, 前移一位
			for (j = high; j > low; --j) // 反向冒泡,找到最小者
				if (arr[j] < arr[j - 1]) {
					ArraysUtils.exchangeElement(arr, j, j - 1);
				}
			++low; // 修改low值,后移一位
		}
	}
	
	/*
	 * 快速排序
	 */
	public static void quickSort(int[] arr,int indexBegin,int indexEnd){
		if(indexBegin<indexEnd){
			int mid=partition(arr,indexBegin,indexEnd);
			quickSort(arr,indexBegin,mid-1);
			quickSort(arr,mid+1,indexEnd);
		}
	}
	
	public static int partition(int[] arr,int low,int high){
		int i=low-1;
		for (int j = low; j < high; j++) {
			if(arr[j]<=arr[high]){
				i++;
				ArraysUtils.exchangeElement(arr, i, j);
			}
		}
		ArraysUtils.exchangeElement(arr, i+1, high);
		return i+1;
	}


	public static int[] midIndex(int[] array,int beginIndex,int endIndex){
		int[] result=new int[2];
		int[] arrayMid=new int[2];
		if(array.length%2==0){//包含两个
			arrayMid[0]=array.length/2-1;
			arrayMid[0]=array.length/2;
		}else {//唯一
			arrayMid[0]=array.length/2;
		}
		if(beginIndex<endIndex){
			int midIndex=partition(array,beginIndex,endIndex);
			if(isInArray(arrayMid,midIndex)){

			}
		}
		return result;
	}

	public static boolean isInArray(int[] arrays,int index){
		for(int i:arrays){
			if(i==index){
				return true;
			}
		}
		return false;
	}
}
