package kr.pre.otag2.study.acmicpc.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LuckyStraight_18406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String inputLeft = input.substring(0, input.length() / 2);
        String inputRight = input.substring(input.length() / 2);

        int sumLeft = 0;
        int sumRight = 0;

        for (char s : inputLeft.toCharArray()) {
            sumLeft += s - '0';
        }

        for (char s : inputRight.toCharArray()) {
            sumRight += s - '0';
        }

        if (sumLeft == sumRight) {
            System.out.println("LUCKY");
            return;
        }

        System.out.println("READY");
        return;
    }
}
