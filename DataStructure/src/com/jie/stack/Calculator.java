package com.jie.stack;

/**
 * Calculator
 * 栈实现综合计算器
 * 仅仅实现加减乘除
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/24 下午9:43
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "30+50-2+1*1";
        exCal(expression, 30, 30);
    }


    /**
     * 计算表达式的值
     *
     * @param expression          表达式
     * @param numStackMaxSize     数栈最大容量
     * @param operateStackMaxSize 符号栈（操作）最大容量
     */
    public static int exCal(String expression, int numStackMaxSize, int operateStackMaxSize) {
        ArrayStack2 numStack = new ArrayStack2(numStackMaxSize);
        ArrayStack2 operateStack = new ArrayStack2(operateStackMaxSize);
        int index = 0;
        char ch;
        int num1;
        int num2;
        int operate;
        int res;
        //记录多位数
        String keepNum = "";
        do {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operateStack.isOperate(ch)) {
                if (!operateStack.isEmpty()) {
                    if (operateStack.priority(ch) <= operateStack.priority(operateStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operate = operateStack.pop();
                        res = numStack.cal(num1, num2, operate);
                        numStack.push(res);
                    }
                }
                    operateStack.push(ch);
            } else {
                //处理多位数
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    int val1 = index + 1;
                    int val2 = index + 2;
                    if (operateStack.isOperate(expression.substring(val1, val2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
        } while (index < expression.length());
        while (!operateStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operate = operateStack.pop();
            res = numStack.cal(num1, num2, operate);
            numStack.push(res);
        }
        int result = numStack.pop();
        System.out.printf("表达式 %s = %d\n", expression, result);
        return result;
    }


}

class ArrayStack2 {
    private final int maxSize;
    private final int[] stack;
    private final char multiply = '*';
    private final char divide = '/';
    private final char subtract = '-';
    private final char add = '+';
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int peek() {
        return stack[top];
    }

    /**
     * 这里定义数值越大，优先级越高
     *
     * @param operate 符号
     * @return 优先级
     */
    public int priority(int operate) {
        if (operate == add || operate == subtract) {
            return 0;
        } else if (operate == multiply || operate == divide) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean isOperate(char val) {
        return val == add || val == subtract || val == divide || val == multiply;
    }

    public int cal(int num1, int num2, int operate) {
        int res = 0;
        switch (operate) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 入栈
     *
     * @param value 入栈的值
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return 出栈的值
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

}