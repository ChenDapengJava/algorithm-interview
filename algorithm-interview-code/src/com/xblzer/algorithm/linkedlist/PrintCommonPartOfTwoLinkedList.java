package com.xblzer.algorithm.linkedlist;

/**
 * 打印两个链表的公共部分
 * @author 行百里者
 * @date 2022-07-14 18:13
 */
public class PrintCommonPartOfTwoLinkedList {
    class Node {
        private int value;
        private Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public void printCommonPart(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            //谁的值小，谁往后移动
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }
}
