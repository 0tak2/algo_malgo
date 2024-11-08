package io.github.otak2.leetcode.grind75;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloodFillTest {
    static FloodFill floodFill = new FloodFill();

    @Test
    void floodFill() {
        int[][] result = floodFill.floodFill(
                new int[][] {
                        {1,1,1},
                        {1,1,0},
                        {1,0,1}
                },
                1, 1, 2
        );

        assertArrayEquals(
                new int[][] {
                        {2, 2, 2},
                        {2, 2, 0},
                        {2, 0, 1}
                },
                result
        );
    }
}