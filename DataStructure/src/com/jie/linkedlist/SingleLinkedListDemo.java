package com.jie.linkedlist;

import java.util.Stack;

/**
 * SingleLinkedListDemo
 * 单向链表
 *
 * @author J
 * @version 1.0
 * @since 2021/2/19 13:41
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(2, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //第一种方式加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        //第二种方式加入
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        //显示
        singleLinkedList.list();

        System.out.println("-----------------------------------------------");
        //测试修改节点
        HeroNode newHeroNOde = new HeroNode(2, "宋宋", "及时雪");
        singleLinkedList.update(newHeroNOde);
        //显示修改后的链表情况
        singleLinkedList.list();
        System.out.println("链表中有效节点个数为：" + getLength(singleLinkedList.getHead()));
        System.out.println("链表中倒数第2个节点为：" + findLastIndexNode(singleLinkedList.getHead(), 2));
        System.out.println("-----------------------------------------------");

        //测试逆序打印单链表
        System.out.println("逆序打印单链表：");
        reversePrint(singleLinkedList.getHead());
        System.out.println("-----------------------------------------------");

        //测试反转链表
        System.out.println("原来链表的情况：");
        singleLinkedList.list();
        reverseLinkedList(singleLinkedList.getHead());
        System.out.println("反转后的链表情况：");
        singleLinkedList.list();

        System.out.println("-----------------------------------------------");
        //测试删除节点
        singleLinkedList.delete(4);
        singleLinkedList.delete(2);
        singleLinkedList.delete(3);
//        singleLinkedList.delete(5);
        singleLinkedList.list();

    }

    /**
     * 获取到单链表的节点的个数（如果是带头节点的链表，不统计头节点）
     *
     * @param head 头节点
     * @return 有效节点个数
     */
    public static int getLength(HeroNode head) {
        //空链表
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 查找链表中倒数第index个节点
     *
     * @param head  头节点
     * @param index
     * @return 链表中倒数第index个节点
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head == null) {
            return null;
        }
        //链表中有效节点的个数
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        //从有效节点开始遍历，遍历次数是size-index次到达指定位置节点
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 将单链表反转
     *
     * @param head 头节点
     */
    public static void reverseLinkedList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，就无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助变量
        //帮助遍历原来的链表
        HeroNode cur = head.next;
        //当前节点【cur】的下一个节点
        HeroNode next = null;
        //新的头节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在reverseHead的最前端
        while (cur != null) {
            //暂时保存当前节点的下一个节点，因为后面需要使用
            next = cur.next;
            //将cur的下一个节点指向新的链表的最前端
            cur.next = reverseHead.next;
            //将新的链表最前端变为cur，就是使reverseHead.next指向cur
            reverseHead.next = cur;
            //让cur后移，指向原来的下一个节点
            cur = next;
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 实现逆序打印单链表
     * 方式1：先将单链表进行反转操作，然后再遍历即可，这样做的问题是会破坏原来的单链表的结构，不建议
     * 方式2：可以利用栈这个数据结构，将各个节点压入栈中，利用栈的后进先出的特点，就实现了逆序打印的效果，原链表没有发生变化
     * 此方法就是使用方式2实现逆序打印单链表
     *
     * @param head 头节点
     */
    public static void reversePrint(HeroNode head) {
        //空链表
        if (head.next == null) {
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> nodeStack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null) {
            nodeStack.push(cur);
            cur = cur.next;
        }
        //将栈中节点进行遍历打印
        while (nodeStack.size() > 0) {
            System.out.println(nodeStack.pop());
        }
    }
}

/**
 * 定义SingleLinkedList 管理HeroNode
 */
class SingleLinkedList {
    /**
     * 初始化头节点，头节点不要动，不存放具体的数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 第一种方式 直接添加到链表的尾部
     * 添加节点到单向链表
     * 当不考虑编号顺序时，1.找到当前链表的最后节点2.将最后节点的next指向新的节点即可
     *
     * @param node 节点
     */
    public void add(HeroNode node) {
        //因为head节点不可以动，所以需要一个辅助变量
        HeroNode temp = head;
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
        temp.next = node;
    }

    /**
     * 第二种方式
     * 根据排名将英雄节点插入到指定位置（如果有这个排名，则添加失败，并给出提示）
     *
     * @param node 节点
     */
    public void addByOrder(HeroNode node) {
        //因为头节点不能动，所以需要一个辅助变量帮助找到指定位置
        HeroNode temp = head;
        //标识添加的排名是否存在，默认为false
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //根据排名找到位置，node在temp和temp.next之间
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄节点编号%d已经存在，不能加入\n", node.no);
        } else {
            //插入到链表中
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 修改节点的信息，根据排名编号来修改，即排名编号不可以修改
     * 根据newHeroNode的no来修改即可
     *
     * @param newHeroNode 新节点
     */
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        //先定义一个辅助变量
        HeroNode temp = head.next;
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
     *
     * @param no 对应的编号
     */
    public void delete(int no) {
        //辅助节点
        HeroNode temp = head;
        //标识是否找到待删除节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("链表中没有找到编号为%d的节点，无法删除\n", no);
        }
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
        HeroNode temp = head.next;
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
}

/**
 * 定义HeroNode，每一个HeroNode对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int hNo, String hName, String hNickname) {
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
//                ", next=" + next +
                '}';
    }
}