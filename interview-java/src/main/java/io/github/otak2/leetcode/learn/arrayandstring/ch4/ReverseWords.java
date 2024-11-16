package io.github.otak2.leetcode.learn.arrayandstring.ch4;

/**
 * Reverse Words in a String
 *
 * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1164/
 *
 * " +"를 기준으로 split 실행해 String 배열 만들고 뒤집기
 * 처믕에는 정규표현식 사용이 가능한지 몰랐고, " "로 split하여 걸러내느라 코드가 복잡해짐
 * " +"으로 나누면 공백이 한 번 이상 반복되는 경우 기준점이 되므로 전처리가 필요 없어짐
 *
 * 6ms, 42.6MB
 */
public class ReverseWords {
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +"); // RegExp

        for (int i=0; i<words.length/2; i++) {
            // a b c d -> d c b a
            // 0 1 2 3
            //
            // 0 <=> 3
            // 1 <=> 2

            // a b c d e -> e d c b a
            // 0 1 2 3 4
            //
            // 0 <=> 4
            // 1 <=> 3
            // i <=> length-1-i

            String tmp = words[i];
            words[i] = words[words.length-1-i];
            words[words.length-1-i] = tmp;
        }

        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(w);
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public String reverseWords_old(String s) {
        String[] wordsRaw = s.trim().split(" ");
        String[] words = new String[wordsRaw.length];
        int index = -1;
        for (int i=0; i<wordsRaw.length; i++) {
            if (wordsRaw[i].trim().isEmpty()) {
                continue;
            }
            words[++index] = wordsRaw[i];
        }
        int length = index + 1;

        for (int i=0; i<length/2; i++) {
            String tmp = words[i];
            words[i] = words[length-1-i];
            words[length-1-i] = tmp;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<length; i++) {
            sb.append(words[i]);
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}