package kr.pe.otag2.study.icote.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class RiceCake_7_8 {
    public void solution() throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        String[] totals = br.readLine().split(" ");
        int sumTarget = Integer.parseInt(totals[1]);

        int[] riceCakes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 0;
        while (height > 0) {
            int heightSnapshot = height;
            int sum = Arrays.stream(riceCakes).map(i -> i - heightSnapshot).filter(i -> i > 0).sum();
            if (sum >= sumTarget) {
                System.out.println(height);
                return;
            }

            height = height / 2;
        }

    }

    public static void main(String[] args) throws IOException {
        RiceCake_7_8 solution = new RiceCake_7_8();
        solution.solution();
    }
}
