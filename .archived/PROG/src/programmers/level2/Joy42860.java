/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42860
 * 조이스틱
 * 몇가지 정확도 검사 실패
 */
package programmers.level2;

import java.util.ArrayList;
import java.util.List;

class Solution42860 {
	public char shiftChar(char c, int direction) {
		direction = direction % 26;
		
		char result = (char) (c + direction);
		
		if (result < 'A') {
			result = (char) ('Z' - ('A' - result - 1));
		}
		
		if (result > 'Z') {
			result = (char) ('A' + (result - 'Z' - 1));
		}
		return result;
	}
	
	public int calcDistanceMin(char current, char target) {
		int distance = target - current;
		int reverseDistace = current - 'A' // 현재 값에서 A까지 거리
				+ 1 // A에서 Z까지 거리
				+ 'Z' - target; // Z에서 목표 값까지 거리
//		System.out.println("정방향 진행시 거리: " + distance);
//		System.out.println("역방향 진행시 거리: " + reverseDistace);
		return Math.min(distance, reverseDistace);
	}
	
	public int solution(String name) {
		int answer = 0;
		
		List<Boolean> visited = new ArrayList<>(name.length());
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<name.length(); i++) {
			sb.append('A');
			if (name.charAt(i) == 'A') {
				visited.add(true);
			} else {
				visited.add(false);
			}
		}
		
		int cur = 0; // 첫번째 글자에서 시작
		while(visited.contains(false)) {
			visited.set(cur, true);
			
			// 원하는 글자로 바꾸기
			char c = sb.charAt(cur);
			char t = name.charAt(cur);
			
			answer += calcDistanceMin(c, t);
			sb.setCharAt(cur, t);
			
			// 다음으로 이동
			int distanceMin = Integer.MAX_VALUE;
			int nextPos = -1;
			for (int i=0; i<visited.size(); i++) {
				boolean visit = visited.get(i);
				if (!visit) {
					int a = Math.min(cur, i);
					int b = Math.max(cur, i);
					
					int distance = Math.min(b - a, a + 1 + ((visited.size()- 1) - b));
					if (distance < distanceMin) {
						distanceMin = distance;
						nextPos = i;
					}
				}
			}
			if (nextPos == -1) break;
			
			answer += distanceMin;
			cur = nextPos;
		}

		return answer;
	}
}

public class Joy42860 {

	public static void main(String[] args) {
		Solution42860 solution = new Solution42860();
		
//		System.out.println(solution.solution("JAN"));
		System.out.println(solution.solution("ZZZAAAZ")); // 기댓값 8, 결과값 9.
	}
}
