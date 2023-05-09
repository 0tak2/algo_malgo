/**
 * 짝지어 제거하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12973
 * 
 * 첫 풀이가 효율성 검사에 통과하지 못해서 다시 짰다.
 * 그런데 짜고 나서 다른 사람들 풀이를 보니 아래의 복잡한 로직이 필요 없다는 것을 알게 되었다...
 */
package programmers.level2;

import java.util.Stack;

// 문자와 그 좌표를 담는 클래스
class Ch {
	char c;
	int pos;

	Ch(char c, int pos) {
		this.c = c;
		this.pos = pos;
	}

	char getC() { return c; }

	int getPos() { return pos; }
}

class Solution12973 {
	public int solution(String s) {
		// 스택을 만든다
		Stack<Ch> st = new Stack<>();
 
		int removed = 0; // 제거된 문자 수
		for (int i = 0; i < s.length(); i++) {
			char currentC = s.charAt(i);
			int pos = i - removed; // 제거된 문자 수를 현재 좌표에 반영한다

			if (!st.empty() // 스택이 비어있지 않고
					&& st.peek().getC() == currentC // 현재 문자가 스택에 있는 문자와 같으며,
					&& st.peek().getPos() - pos == -1 // 제거된 문자 수를 고려했을 떄의 현재 좌표와 스택에 있는 문자의 좌표가 1 차이나는 경우
			) {
				st.pop(); // 문자를 제거 -> 이때 실제 String에서 문자를 제거할 필요는 없다. 그 결과 String을 원하는 것이 아니기 때문.
				removed += 2; // 따라서 제거된 문자 수만 조작하여 다음 반복 시 현재 좌표를 바꾸어준다.
			} else {
				st.push(new Ch(currentC, pos));
			}
		}

		if (removed == s.length()) {
			return 1;
		} else {
			return 0;
		}
		
		// 그러나 굳이 위와 같은 좌 검사 로직을 쓰지 않더라도,
		// 스택에 순서대로 문자를 넣고,
		// 반복해가면서 스택에 있는 문자와 현재 문자가 같은지만 비교하면 된다.
		// '연속된 짝문자'를 제거하라는 조건은 스택에 모든 글자를 순차적으로 넣으므로서 해결된다.
		// => 스택에 대한 감각이 아직 부족
	}
}

public class PairChar12973 {

	public static void main(String[] args) {
		Solution12973 solution = new Solution12973();
		System.out.println(solution.solution("baabaa"));
		System.out.println(solution.solution("cdcd"));
	}
}

/**
 * 정확성  테스트
테스트 1 〉	통과 (0.29ms, 73.6MB)
테스트 2 〉	통과 (18.70ms, 82.6MB)
테스트 3 〉	통과 (17.52ms, 88.4MB)
테스트 4 〉	통과 (19.74ms, 91MB)
테스트 5 〉	통과 (18.49ms, 82.4MB)
테스트 6 〉	통과 (17.67ms, 83.8MB)
테스트 7 〉	통과 (17.64ms, 75.7MB)
테스트 8 〉	통과 (18.68ms, 89.8MB)
테스트 9 〉	통과 (0.30ms, 79.2MB)
테스트 10 〉	통과 (0.45ms, 75.2MB)
테스트 11 〉	통과 (0.34ms, 73.8MB)
테스트 12 〉	통과 (0.49ms, 79.5MB)
테스트 13 〉	통과 (0.29ms, 75.3MB)
효율성  테스트
테스트 1 〉	통과 (81.24ms, 73.4MB)
테스트 2 〉	통과 (57.37ms, 67.8MB)
테스트 3 〉	통과 (63.54ms, 70.5MB)
테스트 4 〉	통과 (68.11ms, 70.6MB)
테스트 5 〉	통과 (67.49ms, 70.8MB)
테스트 6 〉	통과 (64.13ms, 70.7MB)
테스트 7 〉	통과 (68.30ms, 70.7MB)
테스트 8 〉	통과 (63.34ms, 70.8MB)
 */

// 효율성 테스트 실패했던 풀이
/*
class Solution12973 {
	public int solution(String s) {
		int answer = -1;
		while (!s.equals("")) { // System.out.println(s); s =
			removePair(s);
			if (s == null) {
				answer = 0;
				return answer;
			}
		}

		answer = 1;
		return answer;
	}

	private String removePair(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			char current = s.charAt(i);
			char next = s.charAt(i + 1); //
			System.out.println(current + ", " + next);
			if (current == next)
				try {
					return s.substring(0, i) + s.substring(i + 2);
				} catch (IndexOutOfBoundsException e) {
					return s.substring(0, i);
				}
		}
		return null;
	}
}
*/
 