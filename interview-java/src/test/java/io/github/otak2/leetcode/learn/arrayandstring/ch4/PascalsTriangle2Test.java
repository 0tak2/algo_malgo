package io.github.otak2.leetcode.learn.arrayandstring.ch4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PascalsTriangle2Test {
    @Test
    void test0() {
        PascalsTriangle2 pascalsTriangle = new PascalsTriangle2();
        List<Integer> ans = pascalsTriangle.getRow(0);
        assertEquals(List.of(1), ans);
    }

    @Test
    void test1() {
        PascalsTriangle2 pascalsTriangle = new PascalsTriangle2();
        List<Integer> ans = pascalsTriangle.getRow(1);
        assertEquals(List.of(1, 1), ans);
    }

    @Test
    void test2() {
        PascalsTriangle2 pascalsTriangle = new PascalsTriangle2();
        List<Integer> ans = pascalsTriangle.getRow(2);
        assertEquals(List.of(1, 2, 1), ans);
    }

    @Test
    void test3() {
        PascalsTriangle2 pascalsTriangle = new PascalsTriangle2();
        List<Integer> ans = pascalsTriangle.getRow(3);
        assertEquals(List.of(1, 3, 3, 1), ans);
    }

    @Test
    void test4() {
        PascalsTriangle2 pascalsTriangle = new PascalsTriangle2();
        List<Integer> ans = pascalsTriangle.getRow(4);
        assertEquals(List.of(1, 4, 6, 4, 1), ans);
    }
}