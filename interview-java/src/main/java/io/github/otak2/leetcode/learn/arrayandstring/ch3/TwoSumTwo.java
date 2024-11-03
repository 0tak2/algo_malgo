package io.github.otak2.leetcode.learn.arrayandstring.ch3;

import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum II - Input array is sorted
 *
 * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1153/
 *
 * Two Sum I과는 달리 인풋 값이 정렬되어 있으므로 투포인터 사용 가능
 * 양 끝 단에서 시작해, 서로 더했을 때 target보다 작으면 왼쪽 포인터를 오른쪽으로 옮기고, 크면 오른쪽 포인터를 왼쪽으로 옮기는 식으로 구현
 *
 * 2ms, 47.1MB
 */
public class TwoSumTwo {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            int candidate = numbers[i] + numbers[j];

            if (candidate == target) {
                return new int[] {i + 1, j + 1};
            }

            if (candidate > target) {
                j--;
                continue;
            }

            if (target > candidate) {
                i++;
                continue;
            }
        }

        return null;
    }

    /**
     * 인풋 값이 정렬되어 있지 않았던 Two Sum I에서는 이렇게 해시맵을 활용
     * 매번 해시 테이블 입출력을 하게 되서인지 시간 차이가 크다
     *
     * 7ms, 46.6MB
     */
     public int[] twoSum_old(int[] numbers, int target) {
         Map<Integer, Integer> memo = new HashMap<>(); // K: number, V: index

         for (int i=0; i < numbers.length; i++) {
             int j = memo.getOrDefault(target - numbers[i], -1);
             if (j != -1) {
                 int first = Math.min(i + 1, j + 1);
                 int second = Math.max(i + 1, j + 1);
                 return new int[] {first, second};
             }

             memo.put(numbers[i], i);
         }

         return null;
     }
}
