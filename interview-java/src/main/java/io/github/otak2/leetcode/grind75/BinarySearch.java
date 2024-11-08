package io.github.otak2.leetcode.grind75;

/**
 * leetcode 704. Binary Search
 * Grind75 #8
 * https://leetcode.com/problems/binary-search/description/
 *
 * 간단한 이진탐색 문제. while문으로 구현
 *
 * 0ms, 46.20MB
 */
public class BinarySearch {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(end >= 0 && start <= nums.length - 1 && start <= end) {
            int index = start + (end - start) / 2;
            if (target == nums[index]) {
                return index;
            }

            if (target < nums[index]) {
                end = index - 1;
                continue;
            }

            start = index + 1;
        }

        return -1;
    }
}
