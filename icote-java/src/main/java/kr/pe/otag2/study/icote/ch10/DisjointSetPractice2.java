package kr.pe.otag2.study.icote.ch10;

import java.util.Arrays;

public class DisjointSetPractice2 {
    public static void main(String[] args) {
        EnhancedDisjointSet<Integer> set = new EnhancedDisjointSet<>(new Integer[]{2, 3, 5, 9, 8});
        set.union(0, 3);
        set.union(3, 1);
        System.out.println("parent of id 0 (value=2): id=" + set.findParent(0) + " value=" + set.get(set.findParent(0)));
        System.out.println("parent of id 3 (value=9): id=" + set.findParent(3) + " value=" + set.get(set.findParent(3)));
        System.out.println("parent of id 1 (value=3): id=" + set.findParent(1) + " value=" + set.get(set.findParent(1)));
        System.out.println("subset which of parentid is 0: " + Arrays.toString(set.getByParent(0)));
    }
}
