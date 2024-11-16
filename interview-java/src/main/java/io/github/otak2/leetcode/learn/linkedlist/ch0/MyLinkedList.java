package io.github.otak2.leetcode.learn.linkedlist.ch0;

/**
 * Design Linked List
 *
 * https://leetcode.com/explore/learn/card/linked-list/209/singly-linked-list/1290/
 *
 * 링크드리스트를 문제 조건에 맞게 구현
 * 문제를 잘 읽자!
 * invalid한 index라든지 문제에서 시키는대로 해야하는데
 * 마음대로 짜버려서 돌리면서 수정하는데 애를 먹었다
 *
 * todo: 소요 시간이 꽤 걸리는 편... 더 개선할 수 있나?
 *
 * 10ms, 45MB
 */
public class MyLinkedList {
    private Node head;
    private int length;

    private boolean isNull(Node node) {
        if (node == null) {
//            System.out.println("[warn] node is null");
            return true;
        }
        return false;
    }

    public MyLinkedList() {
        length = 0;
    }

    private Node getNode(int index) {
        if (isNull(head)) {
            return null;
        }

        int count = 0;
        Node node = head;
        while(count++ < index) {
            if (node == null) {
                return null;
            }
            node = node.next;
        }

        return node;
    }

    public int get(int index) {
        final Node found = getNode(index);
        if (isNull(found)) {
            // throw new NullPointerException("Node is null");
            return -1;
        }
        return found.val;
    }

    public void addAtHead(int val) {
        if (isNull(head)) {
            head = new Node(val);
            length++;
            return;
        }

        final Node newNode = new Node(val, head);
        head.prev = newNode;
        head = newNode;
        length++;
    }

    public void addAtTail(int val) {
        if (isNull(head)) {
            addAtHead(val);
            return;
        }

        final Node last = getNode(length - 1);
        if (isNull(last)) {

            return;
        }
        last.next = new Node(val, null, last);
        length++;
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }

        if (index == length) {
            addAtTail(val);
            return;
        }

        final Node original = getNode(index);
        if (isNull(original)) {
            return;
        }
        final Node originalPrev = original.prev;

        final Node newNode = new Node(val, original, originalPrev);
        original.prev = newNode;

        if (!isNull(originalPrev)) {
            originalPrev.next = newNode;
        }

        length++;
    }

    public void deleteAtIndex(int index) {
        final Node found = getNode(index);
        if (isNull(found)) {
            return;
        }

        final Node prev = found.prev; // nullable
        final Node next = found.next; // nullable

        if (!isNull(prev)) {
            prev.next = next;
        }

        if (!isNull(next)) {
            next.prev = prev;
        }

        if (index == 0) {
            head = next;
        }

        length--;
    }
}

class Node {
    Node prev;
    Node next;
    int val;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(int val, Node next, Node prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
