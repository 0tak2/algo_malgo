package kr.pe.otag2.study.icote.ch10;

import java.util.ArrayList;
import java.util.List;

public class EnhancedDisjointSet<T> {
    private final int[] parentTable;
    private final T[] data;

    public EnhancedDisjointSet(T[] elements) {
        int[] parentTable = new int[elements.length];
        for (int i=0; i<parentTable.length; i++) {
            parentTable[i] = i;
        }
        this.parentTable = parentTable;

        this.data = elements;
    }

    public void union(int elId1, int elId2) {
        int parent1 = findParent(elId1);
        int parent2 = findParent(elId2);

        if (parent1 < parent2) {
            parentTable[parent2] = parent1; // 원래 부모의 부모를 업데이트
        } else {
            parentTable[parent1] = parent2;
        }
    }

    /**
     * 경로 압축을 적용한 findParent
     * <p>
     * parentTable이 다음과 같다고 해보자.
     * dataId    1  2  3  4  5
     * parentId  1  1  2  3  4
     * <p>
     * 모든 원소 각각의 부모 ID를 찾는다고 할 때, 기존의 findParent의 경우
     * 모든 원소에 대해 불필요한 반복을 수행해야 한다.
     * <p>
     * 다음과 같이 경로 압축을 적용하면, findParent가 재귀호출되면서
     * parentTable을 업데이트 하게 된다.
     * 예컨대 최초로 findTable(5)를 한 번 호출하고 모든 호출 스택이 종료되면, parentTable은 다음과 같을 것이다.
     * dataId    1  2  3  4  5
     * parentId  1  1  1  1  1
     * <p>
     * 노드의 개수가 V개이고, 최대 V-1개의 union 연산과 M개의 find 연산이 가능한 경우,,
     * 경로압출을 적용한 시간 복잡도는 O(V + M(1 + log_2-M/V(V)))임이 저명하다.
     */
    public int findParent(int targetElId) {
        if (parentTable[targetElId] != targetElId) {
            parentTable[targetElId] = findParent(parentTable[targetElId]);
        }
        return parentTable[targetElId];
    }

    public T get(int elId) {
        return data[elId];
    }

    public T[] getByParent(int parentId) {
        List<Integer> elIdList = new ArrayList<>();
        for (int i=0; i<parentTable.length; i++) {
            if (findParent(i) == parentId) {
                elIdList.add(i);
            }
        }

        return (T[])elIdList.stream().map(i -> data[i]).toArray();
    }
}
