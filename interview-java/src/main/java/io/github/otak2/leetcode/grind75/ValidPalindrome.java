package io.github.otak2.leetcode.grind75;

/**
 * leetcode 125. Valid Palindrome
 * Grind75 #5
 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * 2ms, 43.13MB
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] c = new char[s.length()];
        int len = 0;

        for (int i=0; i < s.length(); i++) {
            if ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                c[len++] = s.charAt(i);
            }

            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                c[len++] = (char)(s.charAt(i) - ('a' - 'A'));
            }
        }
        // System.out.println(Arrays.toString(c));

        for (int i=0; i < len/2; i++) {
            if (c[i] != c[len-1-i]) {
                // System.out.println(i);
                // System.out.println(len-1-i);
                return false;
            }
        }

        return true;
    }
}
