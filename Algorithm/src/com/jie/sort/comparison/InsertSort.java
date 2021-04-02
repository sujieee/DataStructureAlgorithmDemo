package com.jie.sort.comparison;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * InsertSort
 * 插入排序算法
 * 简单插入排序存在的问题：从小到大排序，如果后面需要插入的数是较小的数时，后移的次数明显增多，对效率有影响
 *
 * @author sujie
 * @version 1.0
 * @since 2021/4/1 上午6:58
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        String d1Str = sdf.format(d1);
        System.out.println("插入排序前的时间是：" + d1Str);
        insertSort(arr);
        Date d2 = new Date();
        String d2Str = sdf.format(d2);
        System.out.println("插入排序后的时间是：" + d2Str);
    }

    //移位 插入排序
    public static void insertSort(int[] arr) {
        //{101,34,119,1}
//        int insertVal = arr[1];
//        int waitInsertIndex = 1 - 1;
//        //第一轮
//        while (waitInsertIndex >= 0 && insertVal < arr[waitInsertIndex]) {
//            arr[waitInsertIndex + 1] = arr[waitInsertIndex];
//            waitInsertIndex--;
//        }
//        arr[waitInsertIndex + 1] = insertVal;
//        System.out.println("第一轮插入排序：");
//        System.out.println(Arrays.toString(arr));
        //流程 {101,34,119,1}->{101,101,119,1}->{34,101,119,1}->{34,101,119,1}->{34,101,119,119}->{34,101,101,119}->{34,34,101,119}->{1,34,101,119}
        int insertVal = 0;
        int waitInsertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            waitInsertIndex = i - 1;
            //从小到大排序
            while (waitInsertIndex >= 0 && insertVal < arr[waitInsertIndex]) {
                //将大的值后移
                arr[waitInsertIndex + 1] = arr[waitInsertIndex];
                waitInsertIndex--;
            }
            //优化
            if (waitInsertIndex + 1 != i){
                arr[waitInsertIndex + 1] = insertVal;
            }
        }
    }
}
