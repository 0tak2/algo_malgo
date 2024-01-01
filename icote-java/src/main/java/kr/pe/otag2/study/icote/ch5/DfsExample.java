package kr.pe.otag2.study.icote.ch5;

public class DfsExample {
    public void dfs(int[][] graph, int vertex, boolean[] visited) {
        // 1. 방문 처리
        visited[vertex] = true;
        // todo: 방문한 정점에 대해 수행할 작업
        System.out.println("Visit: " + vertex);

        // 2. 인접 정점 각각에 대하여,
        for(int v : graph[vertex]) {
            // 방문 여부 확인 후,
            if (!visited[v]) {
                // 스택에 새로운 작업을 넣음
                dfs(graph, v, visited);
            }
        }
    }

    public static void main(String[] args) {
        DfsExample dfsExample = new DfsExample();
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
        dfsExample.dfs(simpleGraph, 1, new boolean[simpleGraph.length]);
    }
}
