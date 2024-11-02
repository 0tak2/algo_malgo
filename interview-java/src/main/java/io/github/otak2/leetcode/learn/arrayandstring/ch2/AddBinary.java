package io.github.otak2.leetcode.learn.arrayandstring.ch2;

/**
 * Add Binary
 * https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1160/
 *
 * 처음에 a, b를 Long으로 변환해 더하고, 다시 <a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Long.html#toBinaryString(long)">Long.toBinaryString</a>
 * 을 이용해 이진수 문자열로 반환하는 방식으로 구현했지만 특정 케이스에서 오버플로우 발생
 * 직접 이진수를 더하듯 구현
 *
 * 1ms, 42.6MB
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        char[] aSeq = a.toCharArray();
        char[] bSeq = b.toCharArray();
        StringBuilder sb = new StringBuilder();

        int aIdx = aSeq.length - 1;
        int bIdx = bSeq.length - 1;
        int carry = 0; // 받아올림
        while (aIdx >= 0 || bIdx >= 0 || carry > 0) {
            int aNum = aIdx < 0 ? 0 : aSeq[aIdx] - '0';
            int bNum = bIdx < 0 ? 0 : bSeq[bIdx] - '0';

            int sum = aNum + bNum + carry;

            sb.insert(0, sum % 2 == 0 ? '0' : '1');
            carry = sum / 2; // 십진수 덧셈에서 앞자리 수만 올리는 것처럼 자리가 올라가므로 2를 나눠 올려준다

            aIdx--;
            bIdx--;
        }

        return sb.toString();
    }
}
