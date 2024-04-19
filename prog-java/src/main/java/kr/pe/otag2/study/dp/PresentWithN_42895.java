package kr.pe.otag2.study.dp;

// N 1번: N
// N 2번: NN, N + N, N - N, N * N, N / N
// N 3번: 1번 (연산) 2번 또는 2번 (연산) 1번
// N 4번: 1 # 3, 2 # 2, 3 # 1
// ...

import java.util.ArrayList;
import java.util.List;

class PresentWithN_42895 {
    private final int MAX = 8;

    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        int cnt = 0;
        String[] digits = String.valueOf(number).split("");
        for (String d : digits) {
            if (Integer.parseInt(d) == N) {
                cnt++;
            }
        }
        if (cnt == digits.length) {
            return cnt;
        }

        List<List<Integer>> dp = new ArrayList<>(MAX + 1);
        dp.add(new ArrayList<>(0));

        List<Integer> firstList = new ArrayList<>(1);
        firstList.add(N);
        dp.add(firstList);

        for (int i = 2; i <= MAX; i++) {
            List<Integer> opResult = new ArrayList();
            dp.add(opResult);
            int repeatN = 0;
            for (int p = 0; p < i; p++) {
                repeatN += N * Math.pow(10, p);
            }
            opResult.add(repeatN);

            for (int j = 1; j < i; j++) { // 더해서 i가 되는 두 수의 쌍 찾기
                int k = i - j; // (j, k)

                List<Integer> leftOps = dp.get(j);
                List<Integer> rightOps = dp.get(k);

                for (int left : leftOps) {
                    for (int right: rightOps) {
                        if (left + right == number) {
                            return i;
                        } else {
                            opResult.add(left + right);
                        }

                        if (left - right == number) {
                            return i;
                        } else {
                            opResult.add(left - right);
                        }

                        if (left * right == number) {
                            return i;
                        } else {
                            opResult.add(left * right);
                        }

                        if (right == 0) {
                            continue;
                        }

                        if (left / right == number) {
                            return i;
                        } else {
                            opResult.add(left / right);
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        PresentWithN_42895 solution = new PresentWithN_42895();
        int ret1 = solution.solution(5, 12);
        System.out.println(ret1);

        int ret2 = solution.solution(2, 11);
        System.out.println(ret2);

        int ret3 = solution.solution(5, 5);
        System.out.println(ret3);

        int ret4 = solution.solution(5, 555);
        System.out.println(ret4);
    }
}