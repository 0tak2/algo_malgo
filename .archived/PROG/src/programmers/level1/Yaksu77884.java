/** 
 * https://school.programmers.co.kr/learn/courses/30/lessons/77884
 * 약수의 개수와 덧셈
 * 포인트는 제곱근까지 반복하는 것. 소수를 찾아서 이 반복이 더 많아지게 되면, 에라토스테네스의 체를 사용.
 */
package programmers.level1;

class Solution77884 {
	private int cntYaksu(int number) {
		int cnt = 0;
		for (int i = 1; i * i <= number; i++) {
			if (i * i == number)
				cnt++;
			else if (number % i == 0)
				cnt += 2;
		}
		return cnt;
	}

	public int solution(int left, int right) {
		int answer = 0;

		for (int i = left; i <= right; i++) {
			int cnt = cntYaksu(i);
			// System.out.print(i + " -> ");
			// System.out.println(cnt);
			if (cnt % 2 == 0)
				answer += i;
			else
				answer -= i;
		}

		return answer;
	}
}

public class Yaksu77884 {

}
