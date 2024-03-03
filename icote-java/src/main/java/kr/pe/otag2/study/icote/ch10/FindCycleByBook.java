package kr.pe.otag2.study.icote.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindCycleByBook {
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

        boolean err = false;
        for (int i=0; i<totalUnions; i++) {
            inputs = br.readLine().split(" ");
            int node1 = Integer.parseInt(inputs[0]) - 1;
            int node2 = Integer.parseInt(inputs[1]) - 1;

            if (set.findParent(node1) == set.findParent(node2)) {
                err = true;
                break;
            }

            set.union(node1, node2);
        }

        if (err) {
            System.out.println("사이클이 있습니다. 요청한 작업을 수행할 수 없었습니다.");
            return;
        }
        System.out.println("요청한 작업을 모두 수행했습니다.");
    }
}
