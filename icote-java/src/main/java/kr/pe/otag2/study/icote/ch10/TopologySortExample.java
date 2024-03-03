package kr.pe.otag2.study.icote.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 위상정렬
 * <p>
 * 방법
 * 1. 진입차수가 0인 노드를 큐에 넣는다
 * 2. 큐에서 노드를 꺼내 해당 노드에서 출발하는 간선을 제거한다
 * 3. 2단계로 인해 차수가 0이 된 노드를 큐에 넣는다
 * 4. 큐가 빌 때까지 2단계-3단계를 반복한다
 * <p>
 * 시간복잡도
 * 각 정점을 모두 순회하며 차수를 파악하고, 차수가 0인 노드가 출발점인 간선을 제거
 * 따라서, 정점 수를 V, 간선 수를 E라고 하면,
 * O(V + E)
 */
public class TopologySortExample {
    private static List<Integer> topoloygySort(List<List<Integer>> graph, int[] indegree) {
        List<Integer> result = new ArrayList<>(graph.size());

        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=graph.size()-1; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int next = q.poll();
            result.add(next);

            List<Integer> connectedNodes = graph.get(next);
            for (Integer node : connectedNodes) {
                indegree[node]--;
                if (indegree[node] == 0) {
                    q.offer(node);
                }
            }
            // connectedNodes.clear(); // 차수를 계산해서 관리하기 때문에 실제 그래프를 변경할 필요는 없음
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        // 입력 형식
        // {노드 개수} {간선 개수}
        // {출발 노드} {도착 노드}
        // ...
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int totalNodes = Integer.parseInt(inputs[0]);
        int totalEdges = Integer.parseInt(inputs[1]);

        List<List<Integer>> graph = new ArrayList<>(totalNodes + 1);
        graph.add(null); // 0번 인덱스에는 더미 삽입
        for (int i=0; i<totalNodes; i++) {
            graph.add(new ArrayList<Integer>());
        }

        int[] indegreeTable = new int[totalNodes + 1];

        for (int i=0; i<totalEdges; i++) {
            inputs = br.readLine().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);

            graph.get(from).add(to);
            indegreeTable[to]++;
        }

        List<Integer> result = topoloygySort(graph, indegreeTable);

        for (Integer node : result) {
            System.out.print(node + " ");
        }
    }
}
