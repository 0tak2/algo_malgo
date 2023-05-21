package programmers.level2;

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
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<name.length(); i++) {
			sb.append('A');
		}
		
		int cur = 0; // 첫번째 글자에서 시작
		while(sb.indexOf("A") > -1) {
			char c = sb.charAt(cur);
			char t = name.charAt(cur);
			
			answer += calcDistanceMin(c, t);
			sb.setCharAt(cur, t);
			
			
		}

		return answer;
	}
}

public class Joy42860 {

	public static void main(String[] args) {
		Solution42860 solution = new Solution42860();
		
		System.out.println("A: " + (int)'A');
		System.out.println("Z: " + (int)'Z');
		System.out.println(solution.shiftChar('E', -5)); // Z
		System.out.println(solution.shiftChar('W', +5)); // B
		System.out.println(solution.shiftChar('N', +55)); // Q
		System.out.println(solution.shiftChar('K', -56)); // G
		System.out.println(solution.calcDistanceMin('C', 'Q')); // 12, 14
	}
}
