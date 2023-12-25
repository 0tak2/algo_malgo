package kr.pe.otag2.study.icote.ch3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LawOfBigNumber_3_1 {
    /**
     * 큰 수 * K + 작은 수를 하나의 덩어리로 놓고 계산
     */
    public void my_solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int M = Integer.parseInt(inputs[1]);
        int K = Integer.parseInt(inputs[2]);

        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).sorted(Comparator.reverseOrder()).toList();

        int a = numbers.get(0);
        int b = numbers.get(1);

        int numOfTimes = M / (K + 1);
        int left = M % (K + 1);
        System.out.println((a * K + b) * numOfTimes + a * left);
    }

    /**
     * 스텝을 하나씩 밟아가면서 계산. 만약 인풋 개수가 많으면 시간복잡도 제한에 걸릴 수 있음.
     */
    public void book_solution_1() throws IOException {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int M = Integer.parseInt(inputs[1]);
        int K = Integer.parseInt(inputs[2]);

        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).sorted(Comparator.reverseOrder()).toList();

        int a = numbers.get(0);
        int b = numbers.get(1);

        while(true) {
            for (int i = 0; i < K; i++) {
                answer += a;
                M--;
            }
            answer += b;
            M--;

            if (M == 0) break;
        }

        System.out.println(answer);
    }

    /**
     * 첫번째 아이디어와 유사한데, 계산하는 방법이 좀 더 깔끔함
     */
    public void book_solution_2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int M = Integer.parseInt(inputs[1]);
        int K = Integer.parseInt(inputs[2]);

        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt).sorted(Comparator.reverseOrder()).toList();

        int a = numbers.get(0);
        int b = numbers.get(1);

        int numOfATimes = M / (K + 1) * K + M % (K + 1);

        System.out.println(numOfATimes * a + (M - numOfATimes) * b);
    }


    public static void main(String[] args) throws IOException {
        LawOfBigNumber_3_1 solution = new LawOfBigNumber_3_1();
        solution.my_solution();
        solution.book_solution_1();
        solution.book_solution_2();
    }
}
