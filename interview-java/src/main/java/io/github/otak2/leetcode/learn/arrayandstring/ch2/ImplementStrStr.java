package io.github.otak2.leetcode.learn.arrayandstring.ch2;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the Index of the First Occurrence in a String
 *
 * https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1161/
 *
 * 시작점이 같다면 거기서부터 반복해서 확인해야한다. 따라서 이중루프를 돌 수밖에 없을 것 같다...
 * 나름 꾀를 부린다고 StringBuilder를 써봤는데 아주 느렸다...
 *
 * 17ms, 44.9MB
 */
public class ImplementStrStr {
    public int strStr_slow(String haystack, String needle) {
        List<StringBuilder> sbs = new ArrayList<>(haystack.length());

        for (char c : haystack.toCharArray()) {
            sbs.add(new StringBuilder());

            for (int i=0; i < sbs.size(); i++) {
                StringBuilder sb = sbs.get(i);
                sb.append(c);

                if (sb.toString().equals(needle)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 샘플코드... indexOf를 그냥 사용했다
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /*
     * 참고로 String::indexOf의 구현 내용
     *     @IntrinsicCandidate
     *     public static int indexOf(byte[] value, int valueCount, byte[] str, int strCount, int fromIndex) {
     *         byte first = str[0];
     *         int max = (valueCount - strCount);
     *         for (int i = fromIndex; i <= max; i++) {
     *             // Look for first character.
     *             if (value[i] != first) {
     *                 while (++i <= max && value[i] != first);
     *             }
     *             // Found first character, now look at the rest of value
     *             if (i <= max) {
     *                 int j = i + 1;
     *                 int end = j + strCount - 1;
     *                 for (int k = 1; j < end && value[j] == str[k]; j++, k++); // 결국 이중루프를 돈다...
     *                 if (j == end) {
     *                     // Found whole string.
     *                     return i;
     *                 }
     *             }
     *         }
     *         return -1;
     *     }
     */
}
