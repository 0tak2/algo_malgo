package kr.pe.otag2.study.icote.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 그리디라고 생각하지 않고 시뮬레이션이라고 생각하고 접근하는 것이 편할 수 있다.
 * 그림을 그려보면서 순차적으로 다른 무게의 공을 선택하는 과정을 파악하여 그대로 로직으로 옮기면 된다.
 * 전체 조합 결과가 아니라 전체 조합의 수를 구하는 것이 목표라는 것을 의식한다.
 */
public class PickBalls_11_5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int totalBalls = Integer.parseInt(inputs[0]);
        int totalSizes = Integer.parseInt(inputs[1]);

        int[] ballToSize = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int[] amountBySize = new int[11]; // 공의 최대 무게 10
        // key: 무게 value: 개수

        for (int i : ballToSize) {
            amountBySize[i]++;
        }

        int result = 0;

        for (int s = 1; s <= totalSizes; s++) {
            // a가 무게 별로 공을 선택한다고 하자.
            int a = amountBySize[s]; // a는 특정 무게의 공을 선택한다.
            totalBalls -= a;
            int b = totalBalls; // b는 나머지 무게가 다른 공의 개수만큼 선택할 수 있다.

            result += a * b; // 곱한 값을 더한다.

            // 다음 단계로 가면 totalBalls를 이미 감소시켰기 때문에
            // b는 현재 단계에서 a가 선택한 공과 다음 단계에서 a가 선택한 공을 제외하고 선택하게 된다. 그럼 겹치지 않는다.
        }

        System.out.println(result);
    }
}
