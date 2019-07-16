package com.leon.cloud.common.uilts.algorithm;

import com.leon.cloud.common.uilts.ArraysUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by leon on 2018/9/18.
 */
public class SortExample {

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int mid = partition(arr, begin, end);
            quickSort(arr, begin, mid - 1);
            quickSort(arr, mid + 1, end);
        }
    }


    public static int partition(int[] arr, int begin, int end) {
        int target = arr[end];
        while (begin < end) {
            if (arr[begin] < target) {
                begin++;
            }
            arr[end] = arr[begin];
            if (arr[end] > target) {
                end--;
            }
            arr[begin] = arr[end];
        }
        arr[begin] = target;
        return begin;
    }


    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        buildMaxHeap(arr);

    }

    public static void buildMaxHeap(int[] arr) {

    }


    static class RedPackage {
        int remainSize;
        double remainMoney;
    }


    public static double getRandomMoney(RedPackage _redPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_redPackage.remainSize == 1) {
            _redPackage.remainSize--;
            return (double) Math.round(_redPackage.remainMoney * 100) / 100;
        }
        Random r = new Random();
        double min = 0.01; //
        double max = _redPackage.remainMoney / _redPackage.remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        _redPackage.remainSize--;
        _redPackage.remainMoney -= money;
        return money;
    }


    public static void quickSortStack(int[] arr){
        Stack<Integer> functionStack=new Stack<>();
        int start=0;
        int end=arr.length-1;

        functionStack.push(start);
        functionStack.push(end);

        while (!functionStack.empty()){
            int endIndex=functionStack.pop();
            int startIndex=functionStack.pop();

            if(startIndex<endIndex){
                int mid=partition(arr,startIndex,endIndex);

                if(mid<end){
                    functionStack.push(mid+1);
                    functionStack.push(endIndex);
                }

                if(mid>startIndex){
                    functionStack.push(startIndex);
                    functionStack.push(mid-1);
                }
            }


        }
    }



    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 2, 1, 5, 4, 6};
        //quickSort(arr, 0, arr.length - 1);
        quickSortStack(arr);
        ArraysUtils.printArrays(arr);


        RedPackage redPackage = new RedPackage();
        redPackage.remainSize = 6;
        redPackage.remainMoney = 10.0;
        List<Double> money=new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            money.add(getRandomMoney(redPackage));
        }

        double sum=0.0;
        for(Double item:money){
            sum+=item;
            System.out.println(item);
        }
        System.out.println(sum);

    }
}
