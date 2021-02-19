package com.jie.linkedlist;

import java.util.Stack;

/**
 * TestStack
 * 演示栈的基本使用
 *
 * @author J
 * @version 1.0
 * @since 2021/2/19 21:57
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        stringStack.add("tom");
        stringStack.add("jack");
        stringStack.add("mary");
        //出栈
        while (stringStack.size() != 0) {
            //pop() 将栈顶的数据取出
            System.out.println(stringStack.pop());
        }
    }
}
