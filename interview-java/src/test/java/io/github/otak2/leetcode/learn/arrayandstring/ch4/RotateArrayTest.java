package io.github.otak2.leetcode.learn.arrayandstring.ch4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RotateArrayTest {

    @Test
    void rotate1() {
        RotateArray rotateArray = new RotateArray();
        int[] input = {1, 2, 3, 4, 5, 6, 7};
        rotateArray.rotate(input, 3);
        assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, input);
    }

    @Test
    void rotate2() {
        RotateArray rotateArray = new RotateArray();
        int[] input = {-1, -100, 3, 99};
        rotateArray.rotate(input, 2);
        assertArrayEquals(new int[]{3, 99, -1, -100}, input);
    }

    @Test
    void rotate3() {
        RotateArray rotateArray = new RotateArray();
        int[] input = {2, 4, 6, 8, 10};
        rotateArray.rotate(input, 4);
        System.out.println(Arrays.toString(input));
        assertArrayEquals(new int[]{4, 6, 8, 10, 2}, input);
    }

    @Test
    void rotate4() {
        RotateArray rotateArray = new RotateArray();
        int[] input = {1, 2};
        rotateArray.rotate(input, 3);
        System.out.println(Arrays.toString(input));
        assertArrayEquals(new int[]{2, 1}, input);
    }
}