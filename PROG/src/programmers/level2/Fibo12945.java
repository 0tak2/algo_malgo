/**
 * 피보나치 수
 * https://school.programmers.co.kr/learn/courses/30/lessons/12945
 * 
 * 재귀적 표현
 * 큰 수 다루기
 */

package programmers.level2;

import java.util.Map;

class Solution12945Slow {
	int fib(int i) {
		if (i <= 1) {
			return i;
		}

		return fib(i - 1) + fib(i - 2);
	}

	public int solution(int n) {
		return fib(n) % 1234567;
	}
}

class Solution12945Wrong {
	public long solution(int n) {

		long fibs[] = new long[n + 1];
		fibs[0] = 0;
		fibs[1] = 1;

		for (int i = 2; i <= n; i++) {
			fibs[i] = fibs[i - 2] + fibs[i - 1];
		}

		return fibs[n] % 1234567;
	}
}

class Solution12945 {
	public long solution(int n) {

		long fibs[] = new long[n + 1];
		fibs[0] = 0;
		fibs[1] = 1;

		for (int i = 2; i <= n; i++) {
			long tmp = fibs[i - 2] + fibs[i - 1];
			fibs[i] = tmp % 1234567;
		}

		return fibs[n];
	}
}

public class Fibo12945 {

}
