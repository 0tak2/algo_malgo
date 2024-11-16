package io.github.otak2.leetcode.learn.arrayandstring.ch4;

/**
 * Rotate Array
 *
 * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1182/
 *
 * rotateByOne
 * 처음에는 직관적으로 구현 -> 가장 오른쪽 원소를 임시 변수에 할당, 나머지 원소를 하나씩 오른쪽으로 옮김
 * 이 방법은 간단하지만 시간초과
 *
 * rotateByBulk
 * 다음으로는 nums에서 부분배열을 복사하는 방식으로 구현
 * 이 방법은 수행 속도가 빠르지만 nums의 길이가 k보다 짧은 경우
 * OutOfIndex 발생
 *
 * rotate
 * nums와 k의 대소관계에 따라 알맞은 경우를 호출하도록 분기했음
 *
 * 덕분에 아주 복잡하게 구현되었는데, 샘플코드는 간단하지만 다음에 이 코드를 기억해서
 * 풀 수 있을지는 모르겠다
 *     public void rotate(int[] nums, int k) {
 *         k = k % nums.length;
 *         nums = reverse(nums, 0, nums.length - 1);
 *         nums = reverse(nums, 0, k - 1);
 *         nums = reverse(nums, k, nums.length - 1);
 *     }
 *
 *     public int[] reverse(int[] nums, int start, int end) {
 *         while (end > start) {
 *             int temp = nums[end];
 *             nums[end] = nums[start];
 *             nums[start] = temp;
 *             end--;
 *             start++;
 *         }
 *         return nums;
 *     }
 *
 * 2ms, 57.4MB
 */
public class RotateArray {
    public void rotateByOne(int[] nums, int k) {
        int outOfArray = 0;

        for (int i=0; i<k; i++) {
            // i바퀴

            // 마지막 원소를 임시 변수에 보관
            outOfArray = nums[nums.length-1];

            // 한 자리씩 오른쪽으로 이동
            for (int j=nums.length-1; j>0; j--) {
                nums[j] = nums[j-1];
            }

            nums[0] = outOfArray;
        }
    }

    public void rotateByBulk(int[] nums, int k) {
        int[] outOfArray = new int[nums.length-k];
        for (int i=0; i<nums.length-k; i++) {
            outOfArray[i] = nums[i];
        }

        for (int i=0; i<k; i++) {
            nums[i] = nums[nums.length-k+i];
        }

        int index = 0;
        for (int i=k; i<nums.length; i++) {
            nums[i] = outOfArray[index++];
        }
    }

    public void rotate(int[] nums, int k) {
        if (k > nums.length) {
            rotateByOne(nums, k);
            return;
        }

        rotateByBulk(nums, k);
    }
}
