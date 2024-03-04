package kr.pre.otag2.study.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 도시 분할 계획
 * https://www.acmicpc.net/problem/1647
 * 이코테 p. 300
 *
 */
public class BOJ1647_TRIAL {
    private static int findParent(int[] parentTable, int target) {
        if (parentTable[target] != target) {
            parentTable[target] = findParent(parentTable, parentTable[target]);
        }
        return parentTable[target];
    }

    private static void union(int[] parentTable, int node1, int node2) {
        int parent1 = findParent(parentTable, node1);
        int parent2 = findParent(parentTable, node2);

        if (parent1 < parent2) {
            parentTable[parent2] = parent1;
            return;
        }
        parentTable[parent1] = parent2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int totalNodes = Integer.parseInt(inputs[0]);
        int totalEdges = Integer.parseInt(inputs[1]);

        Edge[] edgeCandidates = new Edge[totalEdges];

        for (int i = 0; i < totalEdges; i++) {
            int[] edgeInputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            edgeCandidates[i] = new Edge(edgeInputs[0], edgeInputs[1], edgeInputs[2]);
        }

        // 1차 작업
        // 1. 입력받은 모든 간선을 비용을 기준으로 정렬
        // 2. 비용이 적은 순서대로 돌면서 사이클 발생 여부 판단
        // 3. 사이클 발생하지 않으면 최소 신장 트리에 포함 (Union)
        int costSum = 0;
        List<Edge> edgeReserved = new ArrayList<>(totalEdges + 1);

        Arrays.sort(edgeCandidates);

        int[] parentTable = new int[totalNodes + 1];
        for (int i = 1; i <= totalNodes; i++) {
            parentTable[i] = i;
        }

        for (Edge e : edgeCandidates) {
            if (findParent(parentTable, e.node1()) == findParent(parentTable, e.node2())) {
                continue;
            }
            union(parentTable, e.node1(), e.node2());
            costSum += e.cost();
            edgeReserved.add(e);
        }

        // 2차 작업
        // 가장 비용이 큰 간선을 제외
        costSum -= edgeReserved.get(edgeReserved.size() - 1).cost();
        System.out.println(costSum);

        // 분리된 마을에는 집이 하나 이상만 있으면 된다.
        // 문제를 잘 읽자
    }

//    private record Edge (
//            int node1,
//            int node2,
//            int cost
//    ) implements Comparable<Edge> {
//        @Override
//        public int compareTo(Edge o) {
//            return Integer.compare(cost, o.cost());
//        }
//    }

    private static class Edge implements Comparable<Edge> {
        private final int node1;
        private final int node2;
        private final int cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        public int node1() {
            return node1;
        }

        public int node2() {
            return node2;
        }

        public int cost() {
            return cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost());
        }
    }
}
