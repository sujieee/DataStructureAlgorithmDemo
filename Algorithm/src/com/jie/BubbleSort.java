package com.jie;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * BubbleSort
 * 冒泡排序算法
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/31 下午9:18
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
//        int temp;
        //第一趟排序
//        for (int i = 0; i < arr.length - 1 - 0; i++) {
//            if (arr[i] > arr[i + 1]) {
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//        }
//        System.out.println("第一趟排序的数组：");
//        System.out.println(Arrays.toString(arr));
        //第二趟排序
//        for (int i = 0; i < arr.length - 1 - 1; i++) {
//            if (arr[i] > arr[i + 1]) {
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//        }
//        System.out.println("第二趟排序的数组：");
//        System.out.println(Arrays.toString(arr));
        //第三趟排序
//        for (int i = 0; i < arr.length - 1 - 2; i++) {
//            if (arr[i] > arr[i + 1]) {
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//        }
//        System.out.println("第三趟排序的数组：");
//        System.out.println(Arrays.toString(arr));
        //第四趟排序
//        for (int i = 0; i < arr.length - 1 - 3; i++) {
//            if (arr[i] > arr[i + 1]) {
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//        }
//        System.out.println("第四趟排序的数组：");
//        System.out.println(Arrays.toString(arr));

        //冒泡排序时间复杂度 O(n^2)
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//            System.out.println("第" + (i + 1) + "次排序的数组：");
//            System.out.println(Arrays.toString(arr));
//        }


        //优化
        //标识变量，表示是否进行过交换
//        boolean flag = false;
//        int[] arr = {3, 9, -1, 10, 20};
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    flag = true;
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//            System.out.println("第" + (i + 1) + "趟排序的数组：");
//            System.out.println(Arrays.toString(arr));
//            if (!flag) {
//                break;
//            } else {
//                //重置flag，进行下一趟的判断
//                flag = false;
//            }
//        }

        //测试冒泡排序速度
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,80000)
            arr[i] = (int) (Math.random() * 80000);
        }
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是：" + date1Str);
        bubbleSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + date2Str);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
