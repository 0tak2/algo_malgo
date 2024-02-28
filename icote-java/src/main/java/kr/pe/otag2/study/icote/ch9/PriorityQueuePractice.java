package kr.pe.otag2.study.icote.ch9;

import java.util.PriorityQueue;

public class PriorityQueuePractice {
    public static void main(String[] args) {
        PriorityQueue<Node> q = new PriorityQueue<>();

        for (int i=0; i<10; i++) {
            int rand = (int) (Math.random() * 10);
            q.offer(new Node(rand));
            System.out.println("Offer: " + rand);
        }

        System.out.println();

        for (int i=0; i<10; i++) {
            Node node = q.poll();
            System.out.println("Poll: " + node.getValue());
        }
    }

    private static class Node implements Comparable<Node> {
        private final int value;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Node o) {
//             return this.value - o.getValue(); // caution: overflow

//            if (this.value > o.getValue()) {
//                return 1;
//            }
//
//            if (this.value == o.getValue()) {
//                return 0;
//            }
//
//            return -1;

            return Integer.compare(this.value, o.getValue()); // sugar
        }
    }
}
