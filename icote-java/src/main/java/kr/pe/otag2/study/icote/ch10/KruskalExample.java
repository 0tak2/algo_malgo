package kr.pe.otag2.study.icote.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 크루스칼 알고리즘
 * 가장 적은 비용으로 모든 노드를 연결하는 알고리즘
 * <p>
 * 분류
 * 그리디
 * <p>
 * 방법
 * 1. 간선 데이터를 비용을 기준으로 오름차순 정렬
 * 2. 간선 각각을 순회하면서 현재 간선에 대해 사이클 발생 여부 확인
 * 2-1. 사이클이 발생하지 않으면 최소 신장 트리 포함
 * 2-2. 사이클이 발생하면 최소 신장 트리 포함하지 않음
 * 3. 모든 간선에 대해 2단계 반복
 *
 * 시간복잡도
 * 간선 개수가 E이면, O(ElogE)
 * 정렬에 필요한 O(ELogE)가 가장 크므로, 이외에 순회에 소요되는 시간은 빅O 표기법에서 무시된다.
 */
public class KruskalExample {
    public static void main(String[] args) throws IOException {
        // 입력
        // {총 노드 수} {총 간선 후보 수}
        // {노드1} {노드2} {비용}
        //...

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int totalNodes = Integer.parseInt(inputs[0]);
        int totalEdges = Integer.parseInt(inputs[1]);

        EnhancedDisjointSet<Object> set = new EnhancedDisjointSet<>(new Object[totalNodes]);

        Edge[] candidateEdges = new Edge[totalEdges];
        List<Edge> acceptedEdgeList = new ArrayList<>(totalEdges);
        for (int i=0; i<totalEdges; i++) {
            int[] edgeInputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            candidateEdges[i] = new Edge(edgeInputs[0]-1, edgeInputs[1]-1, edgeInputs[2]);
            // fixme: Set 내에서 실제 아이디와 개념적인 순번이 다름
        }
        Arrays.sort(candidateEdges);

        for (int i=0; i<totalEdges; i++) {
            Edge next = candidateEdges[i];

            if (set.findParent(next.node1()) == set.findParent(next.node2())) {
                continue;
            }

            set.union(next.node1(), next.node2());
            acceptedEdgeList.add(next);
        }

        System.out.println("==== 결과 ====");
        for (Edge e : acceptedEdgeList) {
            System.out.printf("%d번 ==[%d]== %d번\n", e.node1() + 1, e.cost(), e.node2() + 1);
        }
        System.out.println("비용 합계: " + acceptedEdgeList.stream()
                .reduce(0, (accumulated, b) -> accumulated + b.cost(), (a, b) -> a + b)
        );
    }
}
