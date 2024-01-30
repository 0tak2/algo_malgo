package kr.pe.otag2.study.icote.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RiceCake_7_8 {
    public void solution() throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        String[] totals = br.readLine().split(" ");
        int sumTarget = Integer.parseInt(totals[1]);

        int[] riceCakes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 0;
        int end = Arrays.stream(riceCakes).max().orElse(-1);
        int heightCandidate = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            final int finalMid = mid;

            int sum = Arrays.stream(riceCakes).map(i -> i - finalMid).filter(i -> i > 0).sum();
            if (sum < sumTarget) {
                end = mid - 1; // 덜 잘라야 함 (height 줄이기)
            } else {
                heightCandidate = mid; // 정답 가능성 있음. 정답인 경우 다음에 루프에서 빠져나가게 됨
                start = mid + 1; // 더 많이 잘라야 함 (height 늘리기)
            }
        }

        System.out.println(heightCandidate);
    }

    public static void main(String[] args) throws IOException {
        RiceCake_7_8 solution = new RiceCake_7_8();
        solution.solution();
    }
}
