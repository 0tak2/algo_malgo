package kr.pe.otag2.study.icote.ch3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ToBeOne_3_3 {
    public void my_solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        int count = 0;

        while (n > 1) {
            count++;
            if (n % k == 0) {
                n = n / k;
            } else {
                n--;
            }
        }

        System.out.println(count);
    }

    /**
     * n을 k로 나눌 수 있을 때(n이 k의 배수)까지 1씩 뺀다
     * @throws IOException
     */
    public void book_solution_1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        int count = 0;

        while (n >= k) {
            count++;
            if (n % k == 0) {
                n = n / k;
            } else {
                n--;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        ToBeOne_3_3 solution = new ToBeOne_3_3();
        solution.my_solution();
    }
}
