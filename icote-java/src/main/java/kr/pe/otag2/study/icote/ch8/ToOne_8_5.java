package kr.pe.otag2.study.icote.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1로 만들기
 * 예를 들어 27이라는 수를 입력했을 경우, 가능한 연산의 경우를 트리 형태로 그려보자.
 *                                27
 *                    /                        \
 *                   9                         26
 *               /        \             /               \
 *              3          8          13                25
 *            /  \       /  \         |            /          \
 *          [1]   2     4   7        12           5           24
 *                |    / \  |     /   |   \     /   \     /    |   \
 *               [1]  2  3  6    4    6   11  [1]    4   8    12   23
 *                      ...           ...              ...
 * 9 -> 3 -> 1의 경우가 연산 횟수 3으로, 정답 케이스이다.
 * <p>
 * 이렇게 그림을 그려보면, 알 수 있는 것은 다음과 같다.
 * 1. 입력한 숫자부터 말단이 1인 노드까지 엣지의 개수가 가장 작은 경우가 답이 된다.
 * 2. 트리의 일부가 다른 곳에 거듭 반복된다. (2, 3, 4, ... 등)
 * 따라서 다이나믹 프로그래밍으로 접근할 수 있으며, 점화식은 다음과 같다.
 * <p>
 * (i를 1로 만드는데 필요한 최소 연산 횟수를 a_i라고 하면,
 * a_i = min(a_i/5, a_i/3, a_i/2, a_i-1) + 1 (1을 더해 엣지의 수를 세준다)
 */
public class ToOne_8_5 {
    private final Integer[] topdownTable;

    public ToOne_8_5() {
        this.topdownTable = new Integer[30001];
        this.topdownTable[0] = 0;
        this.topdownTable[1] = 0;
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());

        int[] table = new int[number+1];

        for (int i=2; i <= number; i++) {
            int[] candidates = new int[4];

            candidates[0] = table[i-1] + 1;

            if (i % 2 == 0) {
                candidates[1] = table[i/2] + 1;
            }

            if (i % 3 == 0) {
                candidates[2] = table[i/3] + 1;
            }

            if (i % 5 == 0) {
                candidates[3] = table[i/5] + 1;
            }

            table[i] = Arrays.stream(candidates).filter(n -> n > 0).min().orElse(0);
            // 책에서는 1을 뺀 결과를 5로 나눈것, 3으로 나눈 것, 2로 나눈 것과 순차적으로 비교하는데, 이 경우 사이드이펙트가 없는지 모르겠다.
            // 우선은 한 번에 비교하도록 구현하였다.
        }

        System.out.println(table[number]);
    }

    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        System.out.println(topDown(number));
    }

    private int topDown(int source) {
        if (topdownTable[source] != null) {
            return topdownTable[source];
        }

        Integer[] candidates = new Integer[4]; // 할당되지 않은 경우와 실제 0인 경우 구분 위해

        candidates[0] = topDown(source - 1) + 1;

        if (source % 2 == 0) {
            candidates[1] = topDown(source / 2) + 1;
        }

        if (source % 3 == 0) {
            candidates[2] = topDown(source / 3) + 1;
        }

        if (source % 5 == 0) {
            candidates[3] = topDown(source  / 5) + 1;
        }

        int min = Arrays.stream(candidates).filter(i -> i != null).min(Integer::compareTo).orElse(0);
        topdownTable[source] = min;
        return min;
    }

    public static void main(String[] args) throws IOException {
        ToOne_8_5 solution = new ToOne_8_5();
        solution.solution();
        solution.solution2();
    }
}
