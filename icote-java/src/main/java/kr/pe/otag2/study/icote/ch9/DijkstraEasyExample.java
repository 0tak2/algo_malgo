package kr.pe.otag2.study.icote.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 다익스트라 간단한 구현
 * 1. 출발 노드 설정
 * 2. 최단 거리 테이블 초기화
 * 3. 아직 방문하지 않은 노드 중 최단 거리가 가장 짧은 노드 선택
 * 4. 해당 노드를 거쳐서 다른 노드로 가는 비용을 계산해 최단 거리 테이블 갱신
 * 5. 3-4 단계 반복
 */
public class DijkstraEasyExample {
    public int getNextNode(int[] distanceTable, boolean[] visited) {
        int minCost = Integer.MAX_VALUE;
        int nextNode = 0;
        for (int i=1; i<distanceTable.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (distanceTable[i] < minCost) {
                nextNode = i;
            }
        }

        return nextNode;
    }

    public int[] countDistance(List<int[]>[] graph, int startNode) {
        int[] distanceTable = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        for (int i=1; i<distanceTable.length; i++) {
            distanceTable[i] = Integer.MAX_VALUE; // 무한대로 초기화해두기
        }

        // 시작 노드 방문
        visited[startNode] = true;
        List<int[]> nodesConnected = graph[startNode];
        for (int[] edge : nodesConnected) {
            int dest = edge[0];
            int cost = edge[1];
            distanceTable[dest] = cost;
        }
        distanceTable[startNode] = 0;

        // 시작 노드를 제외한 다른 노드들 방문
        for (int i=0; i< graph.length-1; i++) {
            int current = getNextNode(distanceTable, visited);
            visited[current] = true;
            List<int[]> nodesConnectedFromCurrent = graph[current];

            for (int[] edge : nodesConnectedFromCurrent) { // 현재 노드와 연결된 노드들에 대해,
                int newCost = edge[1] + distanceTable[current]; // 현재 노드를 거쳐 연결 노드로 가는 비용을 계산
                if (newCost < distanceTable[edge[0]]) { // 이 비용이 원래 연결 노드까지 가는데 들었던 비용보다 적으면
                    distanceTable[edge[0]] = newCost; // 새로 할당
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

        DijkstraEasyExample solution = new DijkstraEasyExample();

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
}

/*
[입력 예시]
6 11
1
1 2 2
1 3 5
1 4 1
2 3 3
2 4 2
3 2 3
3 6 5
4 3 3
4 5 1
5 3 1
5 6 2

[출력 예시]
출발점 1부터 도착점 1까지의 최단 거리: 0
출발점 1부터 도착점 2까지의 최단 거리: 2
출발점 1부터 도착점 3까지의 최단 거리: 3
출발점 1부터 도착점 4까지의 최단 거리: 1
출발점 1부터 도착점 5까지의 최단 거리: 2
출발점 1부터 도착점 6까지의 최단 거리: 4
 */