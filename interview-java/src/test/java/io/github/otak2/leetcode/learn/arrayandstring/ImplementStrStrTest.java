package io.github.otak2.leetcode.learn.arrayandstring;

import io.github.otak2.leetcode.learn.arrayandstring.ch2.ImplementStrStr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImplementStrStrTest {
    static ImplementStrStr solution = new ImplementStrStr();

    @Test
    void test1() {
        int result = solution.strStr("mississippi", "issip");
        assertEquals(4, result);
    }
}