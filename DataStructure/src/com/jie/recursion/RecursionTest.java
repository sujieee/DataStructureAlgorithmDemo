package com.jie.recursion;

/**
 * RecursionTest
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/26 下午2:18
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
        System.out.println(factorial(4));
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n = " + n);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
