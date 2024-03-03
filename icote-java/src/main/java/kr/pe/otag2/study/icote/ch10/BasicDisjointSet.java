package kr.pe.otag2.study.icote.ch10;

import java.util.ArrayList;
import java.util.List;

public class BasicDisjointSet<T> {
    private final int[] parentTable;
    private final T[] data;

    public BasicDisjointSet(T[] elements) {
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

        int newParent = Integer.min(parent1, parent2);
        parentTable[elId1] = newParent;
        parentTable[elId2] = newParent;
    }

    public int findParent(int targetElId) {
        int elId = targetElId;
        while (true) {
            if (parentTable[elId] == elId) {
                return elId;
            }

            elId = parentTable[elId];
        }
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
