package kr.pre.otag2.study.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Party_1238 {
    private static int[] dijik(int start, List<List<Node>> map) {
        int[] table = new int[map.size()];
        Arrays.fill(table, Integer.MAX_VALUE);
        table[start] = 0;

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            List<Node> connected = map.get(node.to);

            if (table[node.to] < node.cost) { // 이전에 방문했었다면 넘어가기
                continue;
            }

            for (Node n : connected) { // 연결 노드에 대해 거리 업데이트
                int newCost = node.cost + n.cost;

                if (newCost < table[n.to]) {
                    table[n.to] = newCost;
                    q.offer(new Node(n.to, newCost));
                }
            }
        }

        return table;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int numOfTown = Integer.parseInt(inputs[0]);
        int numOfEdge = Integer.parseInt(inputs[1]);
        int startTown = Integer.parseInt(inputs[2]);

        List<List<Node>> map = new ArrayList<>(numOfEdge + 1);
        map.add(new ArrayList<>(0));
        for (int i = 0; i < numOfTown; i++) {
            map.add(new ArrayList<>(numOfTown));
        }

        for (int i = 0; i < numOfEdge; i++) {
            inputs = br.readLine().split(" ");

            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            int cost = Integer.parseInt(inputs[2]);

            List<Node> connectedNodes = map.get(from);
            connectedNodes.add(new Node(to, cost));
        }

        int[] result = dijik(startTown, map);
        int maxDistance = 0;
        for (int i = 1; i < result.length; i++) {
            if (result[i] > maxDistance) {
                maxDistance = result[i];
            }
        }
        System.out.println(maxDistance); // todo: 올 때, 갈 때 따로 계산해서 더해야함. 다익스트라 아닌듯?
    }

    private static class Node implements Comparable<Node> {
        public int to;
        public int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
