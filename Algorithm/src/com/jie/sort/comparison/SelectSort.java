package com.jie.sort.comparison;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SelectSort
 * 选择排序算法
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/31 下午10:15
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        selectSort(arr);
//        System.out.println("选择排序后：" + Arrays.toString(arr));
        //测试选择排序速度
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,80000)
            arr[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date1Str = sdf.format(date1);
        System.out.println("选择排序前的时间是：" + date1Str);
        selectSort(arr);
        Date date2 = new Date();
        String date2Str = sdf.format(date2);
        System.out.println("选择排序后的时间是：" + date2Str);
    }

    public static void selectSort(int[] arr) {
        //101 34 119 1
        //第一轮
//        int minIndex = 0;
//        int min = arr[0];
//        for (int i = 0 + 1; i < arr.length; i++) {
//            if (min > arr[i]) {
//                min = arr[i];
//                minIndex = i;
//            }
//        }
//        arr[minIndex] = arr[0];
//        arr[0] = min;
//        System.out.println("第一轮后：");
//        System.out.println(Arrays.toString(arr));

        //第二轮
//        minIndex = 1;
//        min = arr[1];
//        for (int i = 1 + 1; i < arr.length; i++) {
//            if (min > arr[i]) {
//                min = arr[i];
//                minIndex = i;
//            }
//        }
//        if (minIndex != 1) {
//            arr[minIndex] = arr[1];
//            arr[1] = min;
//        }
//        System.out.println("第二轮后：");
//        System.out.println(Arrays.toString(arr));

        //第三轮
//        minIndex = 2;
//        min = arr[2];
//        for (int i = 2 + 1; i < arr.length; i++) {
//            if (min > arr[i]) {
//                min = arr[i];
//                minIndex = i;
//            }
//        }
//        if (minIndex != 2) {
//            arr[minIndex] = arr[2];
//            arr[2] = min;
//        }
//        System.out.println("第三轮后：");
//        System.out.println(Arrays.toString(arr));

        //选择排序时间复杂度O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
