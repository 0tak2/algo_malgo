package kr.pe.otag2.study.icote.ch12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringReOrder_12_8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();

        List<Character> tokens = new ArrayList<>(input.length());
        int numSum = 0;

        for (char s : input.toCharArray()) {
            if (s >= 'A' && s <= 'Z') {
                tokens.add(s);
                continue;
            }

            if (s >= '0' && s <= '9') {
                numSum += s - '0';
                continue;
            }

            System.out.println("Unexpected Character");
            break;
        }

        Collections.sort(tokens);

        for (char s : tokens) {
            sb.append(s);
        }

        if (numSum != 0) {
            sb.append(numSum);
        }

        System.out.println(sb);
    }
}
