package kr.pe.otag2.study.icote.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class TwoArrays_6_4 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputs[0];
        int k = inputs[1];

        Integer[] array1 = new Integer[n];
        Integer[] array2 = new Integer[n]; // Collections.reverseOrder() 사용 위해 Integer 배열로 정의

        String[] arrayRaw1 = br.readLine().split(" ");
        for (int i=0; i<n; i++) {
            array1[i] = Integer.parseInt(arrayRaw1[i]);
        }

        String[] arrayRaw2 = br.readLine().split(" ");
        for (int i=0; i<n; i++) {
            array2[i] = Integer.parseInt(arrayRaw2[i]);
        }

        Arrays.sort(array1);
        Arrays.sort(array2, Collections.reverseOrder());

        for (int i=0; i<k; i++) {
            if (array1[i] >= array2[i]) { // 바꿀 수가 더 작으면 탈출
                break;
            }

            int tmp = array1[i];
            array1[i] = array2[i];
            array2[i] = tmp;
        }

        System.out.println(Arrays.stream(array1).reduce(0, Integer::sum));
        // System.out.println(Arrays.stream(array1).mapToInt(i -> i).sum()); // IntStream으로 변환하여 sum
    }

    public static void main(String[] args) throws IOException {
        TwoArrays_6_4 solution = new TwoArrays_6_4();
        solution.solution();
    }
}
