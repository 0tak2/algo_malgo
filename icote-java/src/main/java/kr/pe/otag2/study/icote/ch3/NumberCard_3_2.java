package kr.pe.otag2.study.icote.ch3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NumberCard_3_2 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        final int n = Integer.parseInt(inputs[0]);
        final int m = Integer.parseInt(inputs[1]);

        int max = 0;
        for (int i=0; i<n; i++) {
            int minThisRow = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .sorted().findFirst().orElse(0);

            if (max < minThisRow) {
                max = minThisRow;
            }
        }

        System.out.println(max);
    }

    /**
     * 책에서 Math#min, Math#max 사용
     */
    public void solution_2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        final int n = Integer.parseInt(inputs[0]);
        final int m = Integer.parseInt(inputs[1]);

        int max = 0;
        for (int i=0; i<n; i++) {
            int minThisRow = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .sorted().findFirst().orElse(0);

            max = Math.max(max, minThisRow);
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        NumberCard_3_2 solution = new NumberCard_3_2();
        solution.solution_2();
    }
}
