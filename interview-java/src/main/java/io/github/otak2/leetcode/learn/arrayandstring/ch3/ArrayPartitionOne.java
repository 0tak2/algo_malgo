package io.github.otak2.leetcode.learn.arrayandstring.ch3;

import java.util.Arrays;

/**
 * Array Partition I
 *
 * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1154/
 *
 * 문제 조건을 가장 쉽게 만족하려면, 우선 오름차순으로 정렬하고 2개씩 묶은 후, 그룹별 최솟값을 더해주면 됨
 * 그렇게 구현했을 때, 정렬 nlogn + 최솟값 합계 n
 * todo: 투포인터를 사용해서 구현하려면?
 *
 * 12ms, 46.8MB
 */
public class ArrayPartitionOne {
    public int arrayPairSum(int[] nums) {
        int result = 0;

        Arrays.sort(nums);

        for (int i=0; i < nums.length; i+=2) {
            result += nums[i];
        }

        return result;
    }
}
