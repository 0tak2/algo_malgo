package kr.pe.otag2.study.icote.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci_8_1 {
    private final int[] d1;
    private final int[] d2;

    public Fibonacci_8_1() {
        this(100);
    }

    public Fibonacci_8_1(int dSize) {
        this.d1 = new int[dSize];
        this.d2 = new int[dSize];
    }

    // 다이나믹 프로그래밍 적용 안함
    public int fibo0(int x) {
        if (x == 1 || x == 2) {
            return 1;
        }

        return fibo0(x-1) + fibo0(x-2);
    }

    // 탑 다운
    public int fibo1(int x) {
        if (x == 1 || x == 2) {
            return 1;
        }

        if (d1[x] != 0) {
            return d1[x];
        }

        d1[x] = fibo1(x-1) + fibo1(x-2);

        return d1[x];
    }

    // 바텀 업
    public int fibo2(int x) {
        d2[1] = 1;
        d2[2] = 1;

        for (int i=3; i<=x; i++) {
            // x가 1이거나 2라면 애초에 루프가 돌지 않아서 신경 안써도 된다
            d2[i] = d2[i-1] + d2[i-2];
        }

        return d2[x];
    }

    // 함수를 여러 번 호출해야하는 경우 fibo1이 좋을 듯
    // 한 케이스 해결을 위해 단 한 번 호출하는 경우라면 fibo2가 유리

    public static void main(String[] args) throws IOException {
        Fibonacci_8_1 solution = new Fibonacci_8_1();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        System.out.println(solution.fibo0(input));
        System.out.println(solution.fibo1(input));
        System.out.println(solution.fibo2(input));
    }
}
