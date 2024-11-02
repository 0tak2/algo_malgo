package io.github.otak2.leetcode.learn.arrayandstring.ch2;

/**
 * Longest Common Prefix
 *
 * https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1162/
 *
 * 문자열 중 가장 짧은 문자열의 인덱스까지 순회하면서 공통 문자를 StringBuilder에 추가하는 방식으로 구현
 *
 * 1ms, 41.1MB
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int indexMin = strs[0].length();
        StringBuilder sb = new StringBuilder();

        for (String s : strs) {
            if (s.length() < indexMin) {
                indexMin = s.length();
            }
        }

        for (int i=0; i < indexMin; i++) {
            char candidate = strs[0].charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != candidate) {
                    return sb.toString();
                }
            }
            sb.append(candidate);
        }

        return sb.toString();
    }

    /**
     * 샘플
     * 이중루프를 돌기는 하지만 더 최적화되어 있다...
     */
    public String longestCommonPrefix_sample(String[] strs) {
        String commonPrefix = strs[0];
        for(int i = 0; i < strs.length; i++ ) {
            while(!strs[i].startsWith(commonPrefix)) {
                commonPrefix = commonPrefix.substring(0, commonPrefix.length() -1);
            }
        }
        return commonPrefix;
    }
}