package kr.pe.otag2.study.icote.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Message_9_5 {
    private static int[] calc(List<int[]>[] graph, int startNode) {
        int[] distanceTable = new int[graph.length];
        for (int i=1; i < graph.length; i++) {
            distanceTable[i] = Integer.MAX_VALUE;
        }
        distanceTable[startNode] = 0;

        PriorityQueue<ComparableNode> q = new PriorityQueue<>();
        q.add(new ComparableNode(startNode, 0));

        while (!q.isEmpty()) {
            ComparableNode next = q.poll();

            if (distanceTable[next.node()] < next.cost()) {
                continue;
            }

            for (int[] edge : graph[next.node()]) {
                // 출발점에서 현재 노드의 연결 노드까지 가는데 필요한 비용
                //     = 출발점에서 현재 노드까지 드는 비용 + 이 노드에서 연결 노드까지 가는 비용
                int newCost = next.cost() + edge[1];
                if (newCost < distanceTable[edge[0]]) {
                    distanceTable[edge[0]] = newCost;
                    q.offer(new ComparableNode(edge[0], newCost));
                }
            }
        }

        return distanceTable;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int totalNodes = Integer.parseInt(inputs[0]);
        int totalEdges = Integer.parseInt(inputs[1]);
        int startNode = Integer.parseInt(inputs[2]);

        List<int[]>[] graph = new ArrayList[totalNodes + 1]; // startNode -> [destNode, cost]
        for (int i=1; i<=totalNodes; i++) {
            graph[i] = new ArrayList<>(totalNodes + 1);
        }

        for (int i=0; i<totalEdges; i++) {
            int[] inputEdge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<int[]> connectedNodes = graph[inputEdge[0]];
            connectedNodes.add(new int[]{inputEdge[1], inputEdge[2]});
        }

        int[] resultTable = calc(graph, startNode);
        long totalDest = Arrays.stream(resultTable).filter(i -> i < Integer.MAX_VALUE && i > 0).count();
//        long totalCost = Arrays.stream(resultTable).filter(i -> i < Integer.MAX_VALUE && i > 0).sum();
        long maxCost = Arrays.stream(resultTable).max().getAsInt(); // 동시에 메시지를 보내므로, 최대 시간이 총 소요 시간이 된다
        System.out.println(totalDest + " " + maxCost);
    }

    /**
     * 
     * @param node 노드 번호
     * @param cost 출발점에서 해당 노드까지 소요되는 비용
     */
    public record ComparableNode(int node, int cost) implements Comparable<ComparableNode> {
        @Override
        public int compareTo(ComparableNode o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
