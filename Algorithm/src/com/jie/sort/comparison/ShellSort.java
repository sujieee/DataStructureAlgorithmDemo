package com.jie.sort.comparison;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ShellSort
 * 希尔排序算法
 * 缩小增量排序
 *
 * @author sujie
 * @version 1.0
 * @since 2021/4/1 下午5:02
 */
public class ShellSort {
    public static void main(String[] args) {
        //分5组就是2个数作为一组进行比较，分2组就是5个数作为一组进行比较
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort2(arr);
//        System.out.println(Arrays.toString(arr));

        //测试希尔排序
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        String d1Str = sdf.format(d1);
        System.out.println("希尔排序前的时间是：" + d1Str);
        shellSort2(arr);
        Date d2 = new Date();
        String d2Str = sdf.format(d2);
        System.out.println("希尔排序后的时间是：" + d2Str);
    }

    public static void shellSort1(int[] arr) {
        int temp = 0;
        //第一次希尔排序
//        for (int i = 5; i < arr.length; i++) {
//            for (int j = i - 5; j >= 0; j -= 5) {
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
        //第二次希尔排序
//        for (int i = 2; i < arr.length; i++) {
//            for (int j = i - 2; j >= 0; j -= 2) {
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        int insertVal = 0;
//        int waitInsertIndex = 0;
        //最后只有一组了，所以进行插入排序
//        for (int i = 0; i < arr.length - 1; i++) {
//            insertVal = arr[i + 1];
//            waitInsertIndex = i;
//            while (waitInsertIndex >= 0 && insertVal < arr[waitInsertIndex]) {
//                arr[waitInsertIndex + 1] = arr[waitInsertIndex];
//                waitInsertIndex--;
//            }
//            if (waitInsertIndex != i) {
//                arr[waitInsertIndex + 1] = insertVal;
//            }
////            System.out.println(Arrays.toString(arr));
//        }


        //交换式实现希尔排序
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < arr.length; j++) {
                for (int k = j - gap; k >= 0; k -= gap) {
                    if (arr[k] > arr[k + gap]) {
                        temp = arr[k];
                        arr[k] = arr[k + gap];
                        arr[k + gap] = temp;
                    }
                }
            }
        }

//        for (int i = 0; i < arr.length-1; i++) {
//            insertVal = arr[i + 1];
//            waitInsertIndex = i;
//            while (waitInsertIndex >= 0 && insertVal < arr[waitInsertIndex]) {
//                arr[waitInsertIndex + 1] = arr[waitInsertIndex];
//                waitInsertIndex--;
//            }
//            if (waitInsertIndex != i) {
//                arr[waitInsertIndex + 1] = insertVal;
//            }
//        }
    }

    /**
     * 改进希尔排序
     * 交换式->移动式
     * 移位法希尔排序
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        //增量gap，逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //分组之后直接进行插入排序
            //从第gap个元素，逐个对其所在的组进行插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环后，就给temp找到插入的位置位置
                    arr[j] = temp;
                }
            }
        }
    }
}
