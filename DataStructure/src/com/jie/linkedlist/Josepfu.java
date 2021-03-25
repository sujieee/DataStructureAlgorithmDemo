package com.jie.linkedlist;

/**
 * Josepfu
 * 约瑟夫问题
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/24 下午6:49
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList cs = new CircleSingleLinkedList();
        cs.addBoys(5);
        cs.showBoys();
        cs.countBoys(2,2,5);
    }
}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个first节点，当前没有编号
     */
    private Boy first = null;

    /**
     * @param nums 添加Boy节点的总数量
     */
    public void addBoys(int nums) {
        //对nums进行数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoys() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("Boy的编号 %d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 计算Boy出圈的顺序
     *
     * @param startNo  表示从第几个Boy开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个Boy在圈中
     */
    public void countBoys(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误");
            return;
        }
        //辅助变量
        Boy helper = first;
        //定义从helper
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //定义从第几个Boy开始数数
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true) {
            //判断直至最后
            if (helper == first) {
                break;
            }
            //数数
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //数几个后然后到了的出圈
            System.out.printf("Boy %d 出圈\n", first.getNo());
            //有Boy出圈后调整first和helper
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的Boy %d\n",first.getNo());
    }
}

/**
 * 创建一个Boy类，表示一个节点
 */
class Boy {
    /**
     * 编号
     */
    private int no;

    /**
     * 指向下一个节点，默认为null
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}