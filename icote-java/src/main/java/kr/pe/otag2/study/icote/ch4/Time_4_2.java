package kr.pe.otag2.study.icote.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Time_4_2 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        long result = 0;
        for (int i = 0; i <= input; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    if ((String.valueOf(i) + j + k).contains("3")) {
                        result += 1;
                    }
                }
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        Time_4_2 solution = new Time_4_2();
        solution.solution();
    }
}
