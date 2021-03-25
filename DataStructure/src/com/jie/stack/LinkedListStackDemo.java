package com.jie.stack;

import java.util.Scanner;

/**
 * LinkedListStackDemo
 * 链表模拟栈
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/24 下午9:24
 */
public class LinkedListStackDemo{
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("exit：表示退出栈");
            System.out.println("push：表示添加数据到栈（入栈）");
            System.out.println("pop：表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    linkedListStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    linkedListStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = linkedListStack.pop().getNo();
                        System.out.println("取出的数据是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}

class LinkedListStack {
    private int top = 0;
    private int maxSize;
    private Node[] nodes;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        nodes = new Node[maxSize];
    }

    public boolean isFull() {
        return top == maxSize;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(int newVal) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        Node newNode = new Node(newVal);
        top++;
        nodes[top] = newNode;
    }

    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node value = nodes[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i > 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, nodes[i].getNo());
        }
    }

}

class Node {
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}