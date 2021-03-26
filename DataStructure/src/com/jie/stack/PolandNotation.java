package com.jie.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PolandNotation
 * 逆波兰计算器分析和实现
 * 后缀表达式的计算
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/25 下午11:45
 */
public class PolandNotation {
    public static void main(String[] args) {
        //后缀表达式
        //(3+4)*5-6 -> 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.println("计算的结果：" + res);
        String expression = "1+((2+3)*4)-5";
        //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        //[1, 2, 3, +, 4, *, +, 5, -]
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);
        System.out.println(calculate(suffixExpressionList));

        String testp = "1 2 3 + 4 * + 5.1 -";
        System.out.println(getListString(testp));
        System.out.println(calculateFloat(getListString(testp)));
    }

    /**
     * 中缀表达式转换为后缀表达式
     *
     * @param infixExpressionList 中缀表达式的list
     * @return 转换成功的后缀表达式list
     */
    public static List<String> parseSuffixExpressionList(List<String> infixExpressionList) {
        Stack<String> s1 = new Stack<>();
//        Stack<String> s2 = new Stack<>();
        //因为s2中间没有pop操作，所以用ArrayList存储了
        List<String> s2 = new ArrayList<>();
        for (String item : infixExpressionList) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //操作符push到s1中
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * @param expression 将中缀表达式从字符串的形式转换为list形式
     * @return 转换成功后的中缀表达式list
     */
    public static List<String> toInfixExpressionList(String expression) {
        List<String> ls = new ArrayList<>();
        //索引，用于遍历中缀表达式字符串
        int i = 0;
        String str;
        char c;
        do {
            //+ - * /
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //如果是一个数，要考虑多位数的问题
                str = "";
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < expression.length());
        return ls;
    }

    public static List<String> getListString(String suffixExpression) {
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : s) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 计算后缀表达式的值
     *
     * @param ls 后缀表达式的list形式
     * @return
     */
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                //入栈
                stack.push(item);
            } else {
                //pop出两个数值并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num1 - num2;
                } else if ("*".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        //最后留在stack的数据就是表达式运算结果
        return Integer.parseInt(stack.pop());
    }

    public static float calculateFloat(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$")) {
                stack.push(item);
            } else {
                Float num2 = Float.parseFloat(stack.pop());
                Float num1 = Float.parseFloat(stack.pop());
                Float res = 0f;
                if ("+".equals(item)) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num1 - num2;
                } else if ("*".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        return Float.parseFloat(stack.pop());
    }
}

/**
 * 返回运算符对应的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    /**
     * 返回对应的优先级数字
     *
     * @param operation
     * @return
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}