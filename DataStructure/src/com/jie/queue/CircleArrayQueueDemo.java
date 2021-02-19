package com.jie.queue;

import java.util.Scanner;

/**
 * CircleArrayQueueDemo
 * 改进：使可复用
 * 数组模拟环形队列
 *
 * @author J
 * @version 1.0
 * @since 2021/2/19 8:00
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列，设置容量为4，队列有效数据最大为3
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = circleArrayQueue.headQueue();
                        System.out.printf("队列的头数据是：%d\n", headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 数组模拟环形队列
 */
class CircleArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;
    /**
     * 队列头
     * 指向队列的第一个有效数据的位置
     * 起始值为0
     */
    private int front;
    /**
     * 队列尾
     * 指向队列的最后一个有效数据的后一个位置，保留一个空位作为预留，即rear最大为maxSize-1
     * 起始值为0
     */
    private int rear;
    /**
     * 用于存放数据，模拟队列
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     *
     * @param arrMaxSize 数组的最大容量
     */
    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * @return 判断队列有效数据是否已满（有效数据最大个数为maxSize-1，即rear最大为maxSize-1）
     */
    public boolean isFull() {
        //rear最大为maxSize-1，即rear+1为maxSize时，取模为0，正好等于front的话就说明该队列有效数据已满
        return (rear + 1) % maxSize == front;
    }

    /**
     * @return 判断队列是否为空（front指向当前预留的空位的位置时，即没有有效数据时则队列为空，此时rear等于front）
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param n 添加的数据
     */
    public void addQueue(int n) {
        //判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        //当前空位赋值
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        //此处取模就是为了可以复用和使rear所处队列中的位置一定是空位
        //比如front=1 rear=2 maxSize=3 此时保证可以复用的情况，添加的数据一定是arr[2]这个位置，而rear就会移动到空位arr[0]上，取模才能保证正确复用
        //(rear+1)%maxSize使其环形后移
        rear = (rear + 1) % maxSize;
    }

    /**
     * @return 从队列中取数据
     */
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //不为空
        //此处需要分析front是指向队列的第一个元素
        //1.先把front对应的值保存到临时变量
        int value = arr[front];
        //2.将front后移，需要考虑取模
        //比如front=1 rear=2 maxSize=3，取的是arr[1]这个位置的数据，front发生变化，环形后移
        //(front+1)%maxSize就是为了使其环形后移
        front = (front + 1) % maxSize;
        //3.将临时保存的变量返回
        return value;
    }

    /**
     * 显示队列的所有有效数据
     */
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
        }
        //不为空
        //从front开始遍历，即arr[front]开始
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * @return 当前队列中已有（已经存在的）的有效数据个数
     */
    public int size() {
        //比如front=1 rear=2 maxSize=3
        return (rear - front + maxSize) % maxSize;
    }

    /**
     * @return 显示队列的头数据，不是取数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

}