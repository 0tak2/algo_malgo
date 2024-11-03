package io.github.otak2.leetcode.learn.arrayandstring.ch3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubarraySumTest {
    static SubarraySum solution = new SubarraySum();

    @Test
    void test1() {
        int result = solution.minSubArrayLen(7, new int[] {2, 3, 1, 2, 4, 3});
        assertEquals(2, result);
    }

    @Test
    void test2() {
        int result = solution.minSubArrayLen(4, new int[] {1, 4, 4});
        assertEquals(1, result);
    }

    @Test
    void test3() {
        int result = solution.minSubArrayLen(11, new int[] {1, 1, 1, 1, 1, 1, 1, 1});
        assertEquals(0, result);
    }

    @Test
    void test4() {
        int result = solution.minSubArrayLen(11, new int[] {1, 2, 3, 4, 5});
        assertEquals(3, result);
    }

    @Test
    void test5() {
        int result = solution.minSubArrayLen(213, new int[] {12,28,83,4,25,26,25,2,25,25,25,12});
        assertEquals(8, result);
    }

    @Test
    void test6() {
        int result = solution.minSubArrayLen(15, new int[] {5,1,3,5,10,7,4,9,2,8});
        assertEquals(2, result);
    }
}