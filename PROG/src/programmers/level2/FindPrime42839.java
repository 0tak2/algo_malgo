/*
 * 소수 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 * 
 * 에라토스테네스의 체를 사용하지 않고 일단 한 번 풀어본 버전
 * 근데 통과됨
 * */
package programmers.level2;

import java.util.*;

class FindPrime42839 {

	public int solution(String numbers) {
		int answer = 0;
		int[] numArr = new int[numbers.length()];
		int max = 0;

		// 문제가 제공한 numbers(String)으로부터 수 배열 numArr을
		// 만들고 정렬한다.
		for (int i = 0; i < numbers.length(); i++) {
			int num = (int) numbers.charAt(i) - 48;
			numArr[i] = num;
		}
		Arrays.sort(numArr);

		// 우선 문제가 제공한 수로 만들 수 있는 최대 정수를 구한다.
		for (int i = numArr.length - 1; i >= 0; i--) {
			max += numArr[i] * Math.pow(10, i);
		}
		
		// 2부터 최대 정수까지의 정수 범위에서 존재하는 모든 소수를
		// 순회하며, 해당 소수를 문제가 제공한 수로 만들 수 있는지
		// 확인한다.
		for (int i = 2; i <= max; i++) {
			// 소수 이면서도, 주어진 수로 만들 수 있으면 정답이다
			if (isPrime(i) && canMake(i, numArr)) { 
				answer++;
			}
		}

		return answer;
		
		/*
		 	테스트 1 〉	통과 (2.49ms, 76.3MB)
			테스트 2 〉	통과 (178.04ms, 105MB)
			테스트 3 〉	통과 (0.46ms, 75.5MB)
			테스트 4 〉	통과 (60.68ms, 92.1MB)
			테스트 5 〉	통과 (1249.23ms, 99.4MB)
			테스트 6 〉	통과 (0.48ms, 79.4MB)
			테스트 7 〉	통과 (2.25ms, 76.3MB)
			테스트 8 〉	통과 (2856.40ms, 124MB)
			테스트 9 〉	통과 (1.44ms, 77.9MB)
			테스트 10 〉	통과 (255.50ms, 106MB)
			테스트 11 〉	통과 (18.96ms, 77.8MB)
			테스트 12 〉	통과 (10.90ms, 82.9MB)
		 */
	}
	
	/**
	 * 인자로 넘긴 수가 소수인지 판별한다.
	 * 
	 * @param num(int)
	 * @return 소수여부(boolean)
	 */
	private boolean isPrime(int num) {
		if (num < 2)
			return false;

		// 2부터 자신의 제곱근까지의 정수로 나누어 떨어지지 않으면 소수
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;

	}

	/**
	 * num을 numArr로 만들 수 있는지 판별한다.
	 * 
	 * @param num(int)
	 * @param numArr(int[])
	 * @return 가능여부(boolean)
	 */
	private boolean canMake(int num, int[] numArr) {
		// 우선 수 배열을 가지고 맵을 만든다.
		// : 각 수의 Char 코드가 Key이, 수를 몇 개 사용할 수 있는지
		// 개수가 Value인 맵이다.
		Map<Character, Integer> record = new HashMap<>();
		for (int n : numArr) {
			char nCh = (char) (n + 48);
			record.put(nCh, record.getOrDefault(nCh, 0) + 1);
		}
		// ex>
		// numArr {0, 1, 1}
		// record { '0': 1, '1': 2 }

		// num(int)를 문자열로 변환하고, 각 문자별로 순회하면서
		// 만들 수 있는 수인지 판별한다.
		String numStr = Integer.toString(num);
		for (int i = 0; i < numStr.length(); i++) {
			char current = numStr.charAt(i);
			int available = record.getOrDefault(current, 0);
			if (available <= 0) {
				return false;
			} else {
				record.put(current, available - 1);
			}
		}
		return true;
	}
}