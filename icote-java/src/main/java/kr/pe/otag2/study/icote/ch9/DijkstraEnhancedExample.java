package kr.pe.otag2.study.icote.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 다익스트라 간단한 구현
 * 1. 출발 노드 설정
 * 2. 최단 거리 테이블 초기화
 * 3. 아직 방문하지 않은 노드 중 최단 거리가 가장 짧은 노드 선택
 * 4. 해당 노드를 거쳐서 다른 노드로 가는 비용을 계산해 최단 거리 테이블 갱신
 * 5. 3-4 단계 반복
 */
public class DijkstraEnhancedExample {
    public int[] countDistance(List<int[]>[] graph, int startNode) {
        int[] distanceTable = new int[graph.length];
        PriorityQueue<Node> q = new PriorityQueue<>(); // 도달하는데 필요한 최단거리가 계산된 노드들을 저장할 우선순위 큐
        for (int i=1; i<distanceTable.length; i++) {
            distanceTable[i] = Integer.MAX_VALUE; // 무한대로 초기화해두기
        }

        // 시작점: 시작 노드부터 시작 노드까지는 0의 비용이 필요하다고 정의
        distanceTable[startNode] = 0;
        q.offer(new Node(startNode, 0));
        while (!q.isEmpty()) {
            Node next = q.poll();

            if (distanceTable[next.node] < next.cost) { // 계산된 적이 이미 있는 경우
                continue;
            }

            List<int[]> connectedNodes = graph[next.node];
            for (int[] edge : connectedNodes) { // 현재 노드와 연결된 노드들에 대해,
                int newCost = edge[1] + next.cost; // 현재 노드를 거쳐 연결 노드로 가는 비용을 계산
                if (newCost < distanceTable[edge[0]]) { // 이 비용이 원래 연결 노드까지 가는데 들었던 비용보다 적으면
                    distanceTable[edge[0]] = newCost; // 새로 할당
                    q.offer(new Node(edge[0], newCost));
                }
            }
        }

        return distanceTable;
    }

    public static void main(String[] args) throws IOException {
        // 입력 형식
        // {노드 개수} {간선 개수}
        // {처음 출발 노드}
        // {출발 노드} {도착 노드} {비용}
        // {출발 노드} {도착 노드} {비용}
        // 반복

        DijkstraEnhancedExample solution = new DijkstraEnhancedExample();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] config = br.readLine().split(" ");
        int numOfNodes = Integer.parseInt(config[0]);
        int numOfEdges = Integer.parseInt(config[1]);
        int startNode = Integer.parseInt(br.readLine());

        List<int[]>[] graph = new List[numOfNodes + 1]; // graph[출발점]
        for (int i=0; i<=numOfNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=1; i<=numOfEdges; i++) {
            int[] edgeInput = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<int[]> nodesConnected = graph[edgeInput[0]]; // 출발 노드 -> [연결 노드, 비용]
            nodesConnected.add(new int[]{edgeInput[1], edgeInput[2]});
        }

        int[] result = solution.countDistance(graph, startNode);
        for (int i=1; i<result.length; i++) {
            System.out.println("출발점 " + startNode + "부터 " + "도착점 " + i + "까지의 최단 거리: " + result[i]);
        }
    }

    private static class Node implements Comparable<Node> {
        public final int node; // 노드 번호
        public final int cost; // 해당 노드까지 드는 비용

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
