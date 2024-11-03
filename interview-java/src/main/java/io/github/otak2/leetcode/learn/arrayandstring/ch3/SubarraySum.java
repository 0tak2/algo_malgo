package io.github.otak2.leetcode.learn.arrayandstring.ch3;

/**
 * Minimum Size Subarray Sum
 * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1299/
 *
 * 포인터 두 개를 이용해 윈도우를 가지고, 합을 확인하면서 윈도우 크기를 조정하는 식으로 구현했다
 * 주어진 배열의 특정 부분 배열을 찾는 것이기 때문에 정렬을 해서는 안된다.
 *
 * 1ms 샘플 코드도 구현 방법은 비슷한데, left 포인터만 밖에 잡고 for문을 돌리는 정도의 차이가 있다. 이 차이가 왜 유의미한 시간적 차이를 나타내는지는
 * 잘 모르겠다...
 *
 * 2ms, 57.9MB
 */
public class SubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int min = nums.length;

        int i = 0; // [... i ... j ...]
        int j = 0;
        int currentSum = 0;
        while (i <= j && i < nums.length && j < nums.length) {
            currentSum += nums[j];

            if (currentSum >= target) {
                // 조건 만족
                // 윈도우 크기 줄이기 시도
                while(currentSum >= target) {
                    // min 업데이트
                    if (j - i + 1 < min) {
                        min = j - i + 1;
//                        System.out.println("min updated - min=" + min + " i=" + i + " j=" + j);
                    }

                    currentSum -= nums[i++];
                }
            }

            // 부분배열 합이 더 작다
            j++;
        }

        return min > nums.length ? 0 : min;
    }
}
