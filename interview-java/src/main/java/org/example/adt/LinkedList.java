package org.example.adt;

public class LinkedList<T> {
    public static class Node<T> {
        private final T data;
        private Node<T> nextNode;

        public Node(T data, Node<T> nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }

    public LinkedList() {
    }

    private Node<T> _startNode;

    public int size() {
        int length = 0;

        if (_startNode != null) {
            length++;
        }

        Node<T> node = _startNode;

        while(node != null) {
            node = node.getNextNode();
            if (node != null) {
                length++;
            }
        }

        return length;
    }

    private Node<T> getNode(int index) {
        if (index > size() - 1) {
            throw new RuntimeException("인덱스가 길이를 초과했습니다.");
        }

        Node<T> node = _startNode;
        for (int i=0; i < index; i++) {
            node = node.getNextNode();
        }

        return node;
    }

    public T get(int index) {
        return getNode(index).getData();
    }

    public void add(T data) {
        add(size(), data);
    }

    public void add(int index, T data) {
        if (_startNode == null) {
            _startNode = new Node<>(data, null);
            return;
        }

        if (index == 0) {
            Node<T> nextNode = new Node<>(data, null);
            _startNode.setNextNode(nextNode);
            return;
        }

        Node<T> beforeNode = getNode(index - 1);
        Node<T> originalNextNode = beforeNode.getNextNode();
        Node<T> newNode = new Node<>(data, originalNextNode);

        beforeNode.setNextNode(newNode);
    }

    public void delete(int index) {
        Node<T> target = getNode(index);

        if (index == 0) {
            _startNode = _startNode.getNextNode();
            return;
        }

        Node<T> beforeTarget = null;
        Node<T> cursor = _startNode;
        for (int i=1; i < size(); i++) {
            cursor = cursor.getNextNode(); // 1번부터 시작
            if (cursor.getNextNode() == target) {
                beforeTarget = cursor;
            }
        }

        if (beforeTarget == null) {
            throw new RuntimeException("이전 노드를 찾을 수 없습니다.");
        }

        Node<T> afterTarget = target.getNextNode();

        beforeTarget.setNextNode(afterTarget);
    }
}
