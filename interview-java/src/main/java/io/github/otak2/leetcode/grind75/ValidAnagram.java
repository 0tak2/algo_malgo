package io.github.otak2.leetcode.grind75;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 242. Valid Anagram
 * Grind75 #7
 * https://leetcode.com/problems/valid-anagram/description/
 *
 * 처음에는 해시맵을 활용해서 구현했는데 생각보다 속도가 많이 느렸음
 * 샘플 참고해서 해시테이블 대신 배열을 사용하여 다시 구현
 * 알파벳은 그 자체로 순서를 가지기 떄문에, 해시함수 없이도 배열만으로 테이블처럼 활용 가능
 * 이렇게 되면 해시함수를 거치지 않기 때문에 속도가 대폭 빨라지는 듯 함
 */
public class ValidAnagram {
    // 2ms, 43.25MB
    public boolean isAnagram(String s, String t) {
        int[] memo = new int[26];

        for (char c : s.toCharArray()) {
            memo[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            if (memo[c - 'a'] >= 1) {
                memo[c - 'a']--;
            } else {
                return false;
            }
        }

        for (int count : memo) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    // 10ms, 44.86MB
    public boolean isAnagram_slow(String s, String t) {
        Map<Character, Integer> memo = new HashMap<>();

        for (char c : s.toCharArray()) {
            memo.compute(c, (k, v) -> {
                if (v == null) {
                    return 1;
                }

                return v + 1;
            });

            // more slow
            // 14ms, 45.02MB
            //
            // int prev = memo.getOrDefault(c, 0);
            // memo.put(c, prev + 1);
        }

        for (char c : t.toCharArray()) {
            int count = memo.getOrDefault(c, 0);

            if (count >= 1) {
                memo.put(c, count - 1);
            } else {
                return false;
            }
        }

        for (Map.Entry<Character, Integer> entry : memo.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }

        return true;
    }
}
