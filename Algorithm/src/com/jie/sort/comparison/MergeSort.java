package com.jie.sort.comparison;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * MergeSort
 * 归并排序
 *
 * @author sujie
 * @version 1.0
 * @since 2021/4/2 下午7:25
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        //归并排序是需要一个额外的开销
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println(Arrays.toString(arr));
        //测试归并排序的速度
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[arr.length];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String d1Str = sdf.format(date1);
        System.out.println("归并排序前时间：" + d1Str);
        mergeSort(arr, 0, arr.length - 1, temp);
        Date date2 = new Date();
        String d2Str = sdf.format(date2);
        System.out.println("归并排序后时间：" + d2Str);
    }

    /**
     * 分+合
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左不断递归分解，然后回溯
            mergeSort(arr, left, mid, temp);
            //向右不断递归分解，然后回溯
            mergeSort(arr, mid + 1, right, temp);
            //递归分解一次之后就会进行合并一次，然后回溯
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   要进行排序的数组
     * @param left  左边索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        t = 0;
        int tempLeft = left;
        //第一次left=0 right=1
        //第二次left=2 right=3
        //第三次left=0 right=3 【左边递归分解合并完毕】
        //第四次left=4 right=5
        //第五次left=6 right=7
        //第六次left=4 right=7 【右边递归分解合并完毕】
        //第七次left=0 right 【最后合并】
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
