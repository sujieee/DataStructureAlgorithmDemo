package com.jie.sort.comparison;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * RadixSort
 * 基数排序是对传统桶排序的扩展
 * 经典的空间换时间，占用内存很大，当对海量数据进行基数排序时，容易造成OutOfMemoryError
 * 基数排序是稳定的（即在原序列中r[i]==r[j],且r[i]在r[j]之前，那么进行基数排序后，r[i]依然还是在r[j]之前，则称这种排序算法为稳定的）
 * 有负数的数组，尽量不要用基数排序，如果要支持负数，可以利用绝对值去实现相应的基数排序
 *
 * @author sujie
 * @version 1.0
 * @since 2021/4/5 下午9:29
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        //测试基数排序的速度
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[arr.length];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String d1Str = sdf.format(date1);
        System.out.println("基数排序前时间：" + d1Str);
        radixSort(arr);
        Date date2 = new Date();
        String d2Str = sdf.format(date2);
        System.out.println("基数排序后时间：" + d2Str);
    }

    public static void radixSort(int[] arr) {
        //空间换时间
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementsCount = new int[10];
        //假设max是arr数组里面最大的值
        int max = arr[0];
        //得到数组中最大的值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        String maxRadix = "" + max;
        int maxLength = maxRadix.length();
//        for (int i = 0;i < maxLength;i++) {
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
//            for (int j = 0; j < arr.length; j++) {
//                int digitOfElement = arr[j] / ((int) (Math.pow(10, i))) % 10;
//                bucket[digitOfElement][bucketElementsCount[digitOfElement]] = arr[j];
//                bucketElementsCount[digitOfElement]++;
//            }
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementsCount[digitOfElement]] = arr[j];
                bucketElementsCount[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucketElementsCount.length; k++) {
                if (bucketElementsCount[k] != 0) {
                    for (int l = 0; l < bucketElementsCount[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementsCount[k] = 0;
            }
        }
//        //第一轮（个位）
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的个位数
//            int digitOfElement = arr[i] / 1 % 10;
//            //放入到对应的桶中
//            bucket[digitOfElement][bucketElementsCount[digitOfElement]] = arr[i];
//            //记录对应的桶里面的数的数量
//            bucketElementsCount[digitOfElement]++;
//        }
//        int index = 0;
//        //遍历每一个桶，并将桶中的数据放入原数组中
//        for (int k = 0; k < bucketElementsCount.length; k++) {
//            if (bucketElementsCount[k] != 0) {
//                for (int l = 0; l < bucketElementsCount[k]; l++) {
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            //第一轮处理后需要将每个bucketElementsCount[k]=0
//            bucketElementsCount[k] = 0;
//        }
//        //第二轮（十位）
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的十位数
//            int digitOfElement = arr[i] / 10 % 10;
//            //放入到对应的桶中
//            bucket[digitOfElement][bucketElementsCount[digitOfElement]] = arr[i];
//            //记录对应的桶里面的数的数量
//            bucketElementsCount[digitOfElement]++;
//        }
//        int index1 = 0;
//        //遍历每一个桶，并将桶中的数据放入原数组中
//        for (int k = 0; k < bucketElementsCount.length; k++) {
//            if (bucketElementsCount[k] != 0) {
//                for (int l = 0; l < bucketElementsCount[k]; l++) {
//                    arr[index1] = bucket[k][l];
//                    index1++;
//                }
//            }
//            bucketElementsCount[k] = 0;
//        }
//        //第三轮（百位）
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的十位数
//            int digitOfElement = arr[i] / 100 % 10;
//            //放入到对应的桶中
//            bucket[digitOfElement][bucketElementsCount[digitOfElement]] = arr[i];
//            //记录对应的桶里面的数的数量
//            bucketElementsCount[digitOfElement]++;
//        }
//        int index2 = 0;
//        //遍历每一个桶，并将桶中的数据放入原数组中
//        for (int k = 0; k < bucketElementsCount.length; k++) {
//            if (bucketElementsCount[k] != 0) {
//                for (int l = 0; l < bucketElementsCount[k]; l++) {
//                    arr[index2] = bucket[k][l];
//                    index2++;
//                }
//            }
//            bucketElementsCount[k] = 0;
//        }


    }
}
