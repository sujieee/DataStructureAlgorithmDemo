package com.jie.sort.comparison;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * QuickSort
 * 快速排序算法
 * 对冒泡排序的一种改进
 *
 * @author sujie
 * @version 1.0
 * @since 2021/4/1 下午8:41
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-4, 8, 0, -9, 3};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        //对快速排序进行速度测试
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        String d1Str = sdf.format(d1);
        System.out.println("快速排序前的时间是：" + d1Str);
        quickSort(arr, 0, arr.length - 1);
        Date d2 = new Date();
        String d2Str = sdf.format(d2);
        System.out.println("快速排序后的时间是：" + d2Str);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            //左边都是小于pivot的值
            //如果碰到大于或等于pivot的值该while循环就会结束
            while (arr[l] < pivot) {
                l += 1;
            }
            //右边都是大于pivot的值
            //如果碰到小于或等于pivot的值该while循环就会结束
            while (arr[r] > pivot) {
                r -= 1;
            }
            //左右两边已分配完毕，如果刚开始就符合的话就会直接执行该if里面的语句，不符合的话就会执行后面的交换
            if (l >= r) {
                break;
            }
            //前面while循环碰到情况结束，要进行交换值
            //将大于或等于pivot的值与小于或等于pivot的值进行换边
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            //如果前面两个while循环碰到了等于该pivot的值，则要改变索引，
            //不然后面进行该大while循环时，会进行到一个arr[l]==pivot arr[r]==pivot的局面（也就是里面的两个小while都不会进行，l,r没有改变），
            //此时l还是小于r的（不会出现l>=r的情况），大while循环仍然继续，后面的交换也是交换两个相同的值，导致大while一直在循环，所以要处理相等的情况
            //保证之后的大while可以正常结束，不会一直循环下去
            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //l==r时，此时的arr[l]==pivot arr[r]==pivot
        //应该去递归pivot左边和右边的值（不包括当下的pivot）,因为pivot左右两边的值与pivot的大小比较已经确定好了
        //所以要调整索引，也就是向左递归，范围是[left,r]，r是pivot-1 向右递归，范围是[l,right]，l是pivot+1
        //如果包括了pivot，向左右递归时会重合排序，因此导致quickSort一直调用，递归出不来了，导致栈溢出，所以当l==r时，要调整索引
        if (l == r) {
            r -= 1;
            l += 1;
        }
        //向左递归，将pivot左边的所有值进行快速排序
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归，将pivot右边的所有值进行快速排序
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
