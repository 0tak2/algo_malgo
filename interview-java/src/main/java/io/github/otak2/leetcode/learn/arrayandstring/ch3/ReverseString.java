package io.github.otak2.leetcode.learn.arrayandstring.ch3;

/**
 * Reverse String
 *
 * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1183/
 *
 * 투 포인터 활용
 * 양 끝 단에서 시작해서 서로 스왑하며 중앙으로 진행
 * 교차하게 되면 루프 종료
 *
 * 0ms, 49MB
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;

        while (i < j) {
            swap(s, i++, j--);
        }
    }

    public void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}