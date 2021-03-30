package com.jie.recursion;

/**
 * Queen8
 * 八皇后的问题
 * 流程：
 * place()从0开始放置 里面有个for循环 递归回溯 得到一个解法就会进行打印，所以最先打印的就是从小开始的 不是一个解法则不会进行打印
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/30 下午12:56
 */
public class Queen8 {
    int max = 4;
    int[] array = new int[max];
    static int judgeCount;
    static int count;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.place(0);
        System.out.println("一共判断了 " + judgeCount + " 次");
        System.out.println("有 " + count + " 种放置方法");
    }


    private void place(int n) {
        //如果没有得到一个解法，n就不会等于max，就不会执行到print()
        //得到一个解法，就会n==max，然后执行print()
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            //冲突则n不变
            if (judge(n)) {
                // 一直判断到最后一个不冲突时，n+1 == max
                // 递归回溯
                place(n + 1);
            }
        }
    }

    private void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        count++;
        System.out.println();
    }

    /**
     * 判断是否冲突
     *
     * @param n 开始放置皇后的位置
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //array[n]==array[i] 是否是同一列 Math.abs(n-i)==Math.abs(array[n]-array[i]) 同一行相减是否等于同一列 也就是判断任意两个皇后是否在同一条斜线上
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        //不冲突
        return true;
    }
}
