package kr.pe.otag2.study.icote.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 개미 전사
 * 트리 형태로 그리기는 어렵지만, 부분 해를 계산하다 보면 전체 해를 구할 수 있는 문제로,
 * 다이나믹 프로그래밍으로 접근할 수 있다.
 * <p>
 * 예컨대 다음과 같이 입력된다고 하자.
 * 1 2 5 8 2 9
 * <p>
 * 그리고 왼쪽부터 차례대로 해를 구한다고 하자.
 * 1
 *      -> 1
 * 1 2
 *      -> 1만 털거나 2만 털어야 함 -> 2
 * 1 2 5
 *      -> [1, 2]까지 봤을 때 털 수 있는 최대치만 털거나 [1]과 5만 털어야 함 -> 2 또는 5 -> 5
 * 1 2 5 8
 *      -> [1, 2, 5]까지 봤을 때 털 수 있는 최대치만 털거나
 *         [1, 2]까지 봤을 때 털 수 있는 최대치와 8을 털어야 함
 *      -> 5 또는 10 -> 10
 * 1 2 5 8 2
 *      -> [1, 2, 5, 8]까지 봤을 때 털 수 있는 최대치만 털거나
 *         [1, 2, 5]까지 봤을 때 털 수 있는 최대치와 2를 털어야 함
 *      -> 10 또는 7 -> 10
 * 1 2 5 8 2 9
 *      -> [1, 2, 5, 8, 2]까지 봤을 때 털 수 있는 최대치만 털거나
 *         [1, 2, 5, 8]까지 봤을 때 털 수 있는 최대치와 9를 털어야 함
 *      -> 10 또는 19 -> 19
 * <p>
 * i번째 비축량까지 봤을 때의 최적해를 a_i라고 하면, a_i-1와 a_i-2만 따져보면 되는 것이다. i-3번째까지의 최적해는 이미 i-1번쨰, i-2번째 최적해를 구할 때 반영되어 있다.
 */
public class Ant_8_6 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] table = new int[101];

        table[0] = inputs[0];
        table[1] = Math.max(table[0], inputs[1]);

        for (int i=2; i<total; i++) {
            // 1. 직전의 해만을 가진다
            // 2. 직전의 직전 해와 현재 인덱스의 값을 더한 값을 새로운 해로 가진다

            table[i] = Math.max(table[i-1], table[i-2] + inputs[i]);
        }

        System.out.println(table[total-1]);
    }

    public static void main(String[] args) throws IOException {
        Ant_8_6 solution = new Ant_8_6();
        solution.solution();
    }
}
