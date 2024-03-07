package kr.pe.otag2.study.icote.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InvertString_11_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::valueOf)
                .toArray();

        int prev = inputs[0];
        int group0 = prev == 0 ? 1 : 0;
        int group1 = prev == 1 ? 1 : 0;

        for (int i = 1; i < inputs.length; i++) {
            if (prev == inputs[i]) {
                continue;
            }

            prev = inputs[i];
            if (inputs[i] == 0) {
                group0++;
            } else {
                group1++;
            }
        }

        if (group0 < group1) {
            System.out.println(group0);
        } else {
            System.out.println(group1);
        }
    }
}
