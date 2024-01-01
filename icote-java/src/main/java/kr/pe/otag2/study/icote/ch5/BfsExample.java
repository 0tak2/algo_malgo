package kr.pe.otag2.study.icote.ch5;

import java.util.LinkedList;
import java.util.Queue;

public class BfsExample {
    public void bfs(int[][] graph, int vertex, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(vertex);
        visited[vertex] = true; // 1. 시작점을 큐에 넣고 방문처리

        // 큐가 빌 때까지 반복
        while(q.peek() != null) {
            int v = q.poll();
            // todo: 방문한 정점에 대해 수행할 작업
            System.out.println("Visit: " + v);

            for(int num : graph[v]) { // 2. 인접 정점에 각각에 대하여
                if (!visited[num]) { // 방문 여부 확인 후,
                    q.add(num); // 큐에 넣고 방문처리
                    visited[num] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        BfsExample bfsExample = new BfsExample();
        int[][] simpleGraph = new int[][]{
                new int[]{},
                new int[]{2, 3, 8},
                new int[]{1, 7},
                new int[]{1, 4, 5},
                new int[]{3, 5},
                new int[]{3, 4},
                new int[]{7},
                new int[]{2, 6, 8},
                new int[]{1, 7}
        };

        // 시작 노드에 대해 메서드 호출
        bfsExample.bfs(simpleGraph, 1, new boolean[simpleGraph.length]);
    }
}
