package io.github.otak2.leetcode.learn.linkedlist.ch0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    @Test
    void test1() {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        myLinkedList.get(1);              // return 2
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        myLinkedList.get(1);              // return 3
    }

    @Test
    void test2() {
        // ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
        // [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3, 0);
        myLinkedList.deleteAtIndex(2);
        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        int result = myLinkedList.get(4); // 4
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5, 0);
        myLinkedList.addAtHead(6);

        assertEquals(4, result);
    }

    @Test
    void test3() {
        // ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
        // [[],[1],[3],[1,2],[1],[1],[1]]

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);
        assertEquals(2, myLinkedList.get(1)); // 2
        myLinkedList.deleteAtIndex(1);
        assertEquals(3, myLinkedList.get(1)); // 3
    }

    @Test
    void test4() {
        // ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get","get","deleteAtIndex","deleteAtIndex","get","deleteAtIndex","get"]
        // [[],[1],[3],[1,2],[1],[1],[1],[3],[3],[0],[0],[0],[0]]

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);
        assertEquals(2, myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        assertEquals(3, myLinkedList.get(1));
        assertEquals(-1, myLinkedList.get(3));
        myLinkedList.deleteAtIndex(3);
        myLinkedList.deleteAtIndex(0);
        assertEquals(3, myLinkedList.get(0));
        myLinkedList.deleteAtIndex(0);
        assertEquals(-1, myLinkedList.get(0));
    }
}