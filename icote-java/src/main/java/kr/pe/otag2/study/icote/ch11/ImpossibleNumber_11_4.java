package kr.pe.otag2.study.icote.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어를 떠올리기 아주 어려운 문제였다. 상식과 논리보다는 순차적인 분석과 직관으로 접근하는 것이 유리하기 때문인 것 같다. (물론 어떻게든 수학적으로
 * 증명할 수 있겠지만, 수리논술 문제를 푸는 것이 아니기 때문에...)
 * <p>
 * 임의의 int형 변수 target을 잡는다. target은 만들 수 있는지 없는지의 여부를 현재 알 수 없는 수 중 가장 작은 수를 나타낸다고 하자.
 * 이 말은 즉, 1부터 target-1까지의 수는 현재 모두 만들 수 있다는 것이다.
 * <p>
 * 1. target에 초깃값으로 1을 할당한다. 현재 만들 수 있는 수가 무엇인지 아무 것도 알 수 없음을 나타낸다. (target-1인 0은 양의 정수가 아니다)
 * 2. 입력 받은 수를 정렬하여, 가장 작은 수로 target(현재 1)을 만들 수 있는지 확인한다. (가장 작은 수가 1보다 크다면, 즉 1이 주어지지 않았다면 불가능
 * 할 것이다. 이 경우 답은 1이 된다.)
 * 3. 이 단계로 넘어왔다는 것은 가장 작은 수가 1이라는 것이다. 현재 숫자 1을 target에 더한다. target은 2가 되고, 현재 target - 1, 즉 1을 만들
 * 수 있음을 표현한다.
 * 4. 현재 target인 2와 다음으로 작은 수를 비교한다. 예컨대 다음 수가 또 다시 1이라고 하자. 현재 숫자 1을 다시 target에 더한다. target은 3이 되고,
 * 현재 1, 2를 만들 수 있음을 표현한다.
 * 5. 현재 target인 3과 다음으로 작은 수를 비교한다. 이번에는 다음 수가 3이라고 하자. target은 만들 수 있는 수이며, 현재 숫자 3을 target에
 * 더한다. target은 6이 된다. 현재 숫자를 더하는 목적을 여기서 직관적으로 알 수 있다. 이전 target 3은 1부터 2까지 만들 수 있다는 소리인데,
 * 각각에 현재 숫자 3을 더해보면 추가로 3, 4, 5를 만들 수 있다는 것을 알 수 있다. 1부터 target-1까지 만들 수 있는 것이다.
 * 6. 현재 target은 6인데, 다음 수가 5라고 해보자. 만들 수 있는 수 1, 2, 3, 4, 5 각각에 5를 더해보면 또 다시 10까지 연속된 시퀀스를 만들 수 있다.
 * 즉, target-1은 10이므로, target은 11이 된다. 이런 식으로 target에 현재 수를 더하는 방식을 직관적으로 알 수 있었다. 논리적으로 이해되지는 않지만
 * 현재 상황에서 가장 좋은 방향을 택하는 그리디의 방식에는 부합한다.
 * 7. 현재 target은 11, 다음 수는 12라고 해보자. 12로는 11을 만들 수 없다. 즉, 다음 단계로 갔을 때 입력에서 뽑은 수가 target보다 크면 target을
 * 만들 수 없으므로, 이때 뽑은 수가 정답(만들 수 없는 가장 작은 양의 정수)이 된다.
 */
public class ImpossibleNumber_11_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        int[] coins = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        Arrays.sort(coins);

        int target = 1; // 1 ... target-1까지 만들 수 있음
        for (int i : coins) {
            if (target < i) {
                break;
            }

            target += i;
        }

        System.out.println(target);
    }
}
