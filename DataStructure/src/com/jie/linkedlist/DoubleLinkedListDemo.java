package com.jie.linkedlist;

/**
 * DoubleLinkedListDemo
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/23 下午11:11
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return this.head;
    }

    /**
     * 显示链表【遍历】
     */
    public void list() {
        //1.如果链表为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //2.因为头节点不可以动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到达链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //后移
            temp = temp.next;
        }
    }

    /**
     * 添加一个新的节点到双向链表的最后
     *
     * @param node
     */
    public void add(HeroNode2 node) {
        //因为head节点不可以动，所以需要一个辅助变量
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 修改几乎和单向链表一样，只是节点类型不一样了
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        //先定义一个辅助变量
        HeroNode2 temp = head.next;
        //表示是否找到该节点
        boolean flag = false;
        while (true) {
            //已经遍历完链表
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("链表中没有找到编号为%d的节点，无法修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除编号对应的节点
     * 对于双向链表可以直接找到要删除的这个节点，找到以后进行自我删除即可
     *
     * @param no 对应的编号
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        //辅助节点
        HeroNode2 temp = head.next;
        //标识是否找到待删除节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点就不执行temp.next.pre=temp.pre，否则就会报空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("链表中没有找到编号为%d的节点，无法删除\n", no);
        }
    }
}

/**
 * 定义一个HeroNode2，每个HeroNode2对象都是一个节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点
     */
    public HeroNode2 next;
    /**
     * 指向上一个节点
     */
    public HeroNode2 pre;

    public HeroNode2(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}