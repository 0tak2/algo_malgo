package io.github.otak2.leetcode.learn.arrayandstring.ch4;

/**
 * Reverse Words in a String III
 *
 * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1165/
 *
 * " "를 기준으로 s를 slpit하여 단어로 이뤄진 배열을 만든다.
 * 각 단어를 순회하면서 StringBuilder의 내장 메서드인 reverse를 활용해 뒤집는다.
 * 뒤집은 단어들을 다시 " "로 연결한다.
 *
 * StringBuilder::reverse를 사용했으나, char[]로 바꿔 뒤집을 수도 있을 것 같다.
 * 다만 StringBuilder::reverse의 구현을 볼 떄, 성능 차이는 StringBuilder의 인스턴스를 만드는 비용을 제외하면 크게 없어보인다.
 * 참고: https://chatgpt.com/share/67381a90-8408-800a-98c9-944c9ca96308
 *
 *
 * 4ms, 45MB
 */
public class ReverseWords3 {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(new StringBuilder(word).reverse());
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}