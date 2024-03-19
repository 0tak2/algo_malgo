package kr.pre.otag2.study.acmicpc.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 큰 차이 없다
 */
public class LuckyStraight_18406_Fast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int sumLeft = 0;
        int sumRight = 0;

        // 루프 하나만 사용
        for (int i = 0; i < input.length() / 2; i++) {
            sumLeft += input.charAt(i); // 합을 비교할 것이므로 '0'을 빼지 않아도 된다
            sumRight += input.charAt((input.length() / 2) + i);
        }

        if (sumLeft == sumRight) {
            System.out.println("LUCKY");
            return;
        }

        System.out.println("READY");
        return;
    }
}
