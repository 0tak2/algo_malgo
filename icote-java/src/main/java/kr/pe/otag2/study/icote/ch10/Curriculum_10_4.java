package kr.pe.otag2.study.icote.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Curriculum_10_4 {
    private static int[] sort(List<List<Integer>> graph, int[] indegrees, int[] costs) {
//        List<Integer> sorted = new ArrayList<>(graph.size());

        // 단건 비용 배열로부터 축적 비용 테이블 초기화 (복사)
        int[] accumulatedCosts = new int[costs.length];
        // 1. 직접 순회
//        for (int i = 0; i < costs.length; i++) {
//            accumulatedCosts[i] = costs[i];
//        }
        // 2. clone
//        accumulatedCosts = costs.clone();
        // 3. System.arraycopy
        System.arraycopy(costs, 0, accumulatedCosts, 0, costs.length);

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
//            sorted.add(node);

            List<Integer> connectedNodes = graph.get(node);
            for (int n : connectedNodes) {
                // 연결된 강의에 대해 소요 시간을 업데이트
                // 연결 강의의 현재 축적 시간과 v.s. 연결 강의의 단일 필요 시간 + 큐에서 뽑은 강의 축적 시간 중 더 큰 값
                accumulatedCosts[n] = Integer.max(accumulatedCosts[n], accumulatedCosts[node] + costs[n]);
                indegrees[n]--;
                if (indegrees[n] == 0) {
                    q.offer(n);
                }
            }
        }

        return accumulatedCosts;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNodes = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>(totalNodes + 1);
        graph.add(null); // 0번 노드는 더미

        int[] costs = new int[totalNodes + 1];
        int[] indegrees = new int[totalNodes + 1];
        for (int i = 1; i <= totalNodes; i++) {
            graph.add(new ArrayList<>(totalNodes)); // 초기화

            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            costs[i] = inputs[0];


            List<Integer> connectedNodes = new ArrayList<>(totalNodes + 1);
            for (int j = 1; j < inputs.length; j++) {
                if (inputs[j] == -1) {
                    break;
                }

                graph.get(inputs[j]).add(i);
                indegrees[i]++;
            }
            graph.add(connectedNodes);
        }

        int[] accumulatedCosts = sort(graph, indegrees, costs);
        for (int i = 1; i < accumulatedCosts.length; i++) {
            System.out.println(accumulatedCosts[i]);
        }
    }
}
