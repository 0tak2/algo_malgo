package io.github.otak2.leetcode.grind75;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloodFillVerboseTest {
    static FloodFillVerbose solution = new FloodFillVerbose();

    @Test
    void testWithStack() {
        int[][] result = solution.floodFill(
                new int[][] {{1,1,1},{1,1,0},{1,0,1}},
                1, 1,
                2,
                true
        );

         assertArrayEquals(new int[][] {{2,2,2},{2,2,0},{2,0,1}},
                result);
    }
    /* result:
        visited: 1, 1
        offered: 0, 1
        offered: 1, 0
        Current St/Q: (1, 0) (0, 1)

        visited: 1, 0
        offered: 0, 0
        offered: 2, 0
        Current St/Q: (2, 0) (0, 0) (0, 1)

        visited: 2, 0
        Current St/Q: (0, 0) (0, 1)

        visited: 0, 0
        Current St/Q: (0, 1)

        visited: 0, 1
        offered: 0, 2
        Current St/Q: (0, 2)

        visited: 0, 2
        Current St/Q:
     */

    @Test
    void testWithQueue() {
        int[][] result = solution.floodFill(
                new int[][] {{1,1,1},{1,1,0},{1,0,1}},
                1, 1,
                2,
                false
        );

        assertArrayEquals(new int[][] {{2,2,2},{2,2,0},{2,0,1}},
                result);
    }
    /* result:
        visited: 1, 1
        offered: 0, 1
        offered: 1, 0
        Current St/Q: (0, 1) (1, 0)

        visited: 0, 1
        offered: 0, 0
        offered: 0, 2
        Current St/Q: (1, 0) (0, 0) (0, 2)

        visited: 1, 0
        offered: 0, 0
        offered: 2, 0
        Current St/Q: (0, 0) (0, 2) (0, 0) (2, 0)

        visited: 0, 0
        Current St/Q: (0, 2) (0, 0) (2, 0)

        visited: 0, 2
        Current St/Q: (0, 0) (2, 0)

        visited: 0, 0
        Current St/Q: (2, 0)

        visited: 2, 0
        Current St/Q:
     */
}