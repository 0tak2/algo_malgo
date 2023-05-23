/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42746
 * 가장 큰 수
 * 
 * 정렬, Comparator 사용
 * int[] -> Integer[]
 */

package programmers.level2;

import java.util.Arrays;

class Solution42746 {
	public String solution(int[] numbersInput) {
		String answer = "";
		
		Integer numbers[] = Arrays.stream(numbersInput).boxed().toArray(Integer[]::new); 
		
		Arrays.sort(numbers, (o1, o2) -> {
			String s1 = String.valueOf(o1) + String.valueOf(o2); 
			String s2 = String.valueOf(o2) + String.valueOf(o1);
			
			return -1 * s1.compareTo(s2);
		});
		
		for (int num : numbers) {
			answer += String.valueOf(num);
		}
		
		String[] letters = answer.split(""); // [0, 0, 0, 0] -> "0" Not "0000"
		int zeroCnt = 0;
		for (String s : letters) {
			if ("0".equals(s)) zeroCnt++;
		}
		if (zeroCnt == letters.length) return "0";
		
		return answer;
	}
}

public class Max42746 {

}
