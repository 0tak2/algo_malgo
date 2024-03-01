package kr.pe.otag2.study.icote.ch10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisjointSetPractice1 {
    public static void main(String[] args) {
        BasicDisjoinSet<Integer> set = new BasicDisjoinSet<>(new Integer[]{2, 3, 5, 9, 8});
        set.union(0, 3);
        set.union(3, 1);
        System.out.println("parent of id 0 (value=2): id=" + set.findParent(0) + " value=" + set.get(set.findParent(0)));
        System.out.println("parent of id 3 (value=9): id=" + set.findParent(3) + " value=" + set.get(set.findParent(3)));
        System.out.println("parent of id 1 (value=3): id=" + set.findParent(1) + " value=" + set.get(set.findParent(1)));
        System.out.println("subset which of parentid is 0: " + Arrays.toString(set.getByParent(0)));
    }
}

class BasicDisjoinSet<T> {
    private final int[] parentTable;
    private final T[] data;

    public BasicDisjoinSet(T[] elements) {
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
//        int[] elIds = Arrays.stream(parentTable)
//                .filter(i -> findParent(i) == parentId)
//                .toArray();
//

        List<Integer> elIdList = new ArrayList<>();
        for (int i=0; i<parentTable.length; i++) {
            if (findParent(i) == parentId) {
                elIdList.add(i);
            }
        }

        return (T[])elIdList.stream().map(i -> data[i]).toArray();
    }
}