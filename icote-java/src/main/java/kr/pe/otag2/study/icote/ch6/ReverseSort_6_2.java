package kr.pe.otag2.study.icote.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class ReverseSort_6_2 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Integer[] array = new Integer[N];
        for (int i=0; i<N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        // Arrays.sort는 제네릭으로 타입을 받기 때문에 래퍼 타입의 배열로 처리해야 한다
        Arrays.sort(array, Collections.reverseOrder());

        for (int i=0; i<N; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        ReverseSort_6_2 solution = new ReverseSort_6_2();
        solution.solution();
    }
}
