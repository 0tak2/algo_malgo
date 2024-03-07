package kr.pre.otag2.study.acmicpc.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MultiplyOrPlus_11_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::valueOf)
                .toArray();
        Arrays.sort(inputs);

        int result = inputs[inputs.length - 1];
        for (int i = inputs.length - 2; i >= 0; i--) {
            int current = inputs[i];
            if (current > 1) {
                result *= current;
            } else {
                result += current;
            }
        }

        System.out.println(result);
    }
}
