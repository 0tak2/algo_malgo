package kr.pe.otag2.study.icote.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindCycleImplByMyself {
    public static void main(String[] args) throws IOException {
        // 입력 형식
        // {전체 노드 수} {전체 간선(union) 수}
        // {노드1} {노드2}
        // ...

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int totalNodes = Integer.parseInt(inputs[0]);
        int totalUnions = Integer.parseInt(inputs[1]);

        EnhancedDisjointSet<Integer> set = new EnhancedDisjointSet<>(new Integer[totalNodes]);

        for (int i=0; i<totalUnions; i++) {
            inputs = br.readLine().split(" ");
            int node1 = Integer.parseInt(inputs[0]) - 1;  // fixme: Set 내에서 실제 아이디와 개념적인 순번이 다름
            int node2 = Integer.parseInt(inputs[1]) - 1;
            set.union(node1, node2);
        }

        int parentId = -1;
        boolean isCycle = true;
        for (int i=0; i<totalNodes; i++) {
            if (parentId == -1) {
                parentId = set.findParent(i);
                continue;
            }

            if (parentId != set.findParent(i)) {
                isCycle = false;
                break;
            }
        }

        System.out.println("사이클 여부: " + isCycle);
    }
}
