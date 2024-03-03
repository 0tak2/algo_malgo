package programmers.level1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 카드 뭉치
 * https://school.programmers.co.kr/learn/courses/30/lessons/159994
 * @author limo
 *
 */
public class Cards159994 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
		Queue<String> q1 = new LinkedList<>();
		Queue<String> q2 = new LinkedList<>();
		for (String s : cards1) {
			q1.offer(s);
		}
		for (String s : cards2) {
			q2.offer(s);
		}
		
		for (String s: goal) {
//			System.out.println(s);
			if (q1.peek() != null && q1.peek().equals(s)) {
//				System.out.println("q1\n");
				q1.poll();
				continue;
			} else if (q2.peek() != null && q2.peek().equals(s)) {
//				System.out.println("q2\n");
				q2.poll();
				continue;
			} else {
				return "No";
			}
		}
		
		return "Yes";
    }
    
    public static void main(String[] args) {
		Cards159994 sol = new Cards159994();
		
		String answer1 = sol.solution(
				new String[]{"i", "drink", "water"},
				new String[]{"want", "to"},
				new String[]{"i", "want", "to", "drink", "water"});
		System.out.println(answer1);
		
		String answer2 = sol.solution(
				new String[]{"i", "water", "drink"},
				new String[]{"want", "to"},
				new String[]{"i", "want", "to", "drink", "water"});
		System.out.println(answer2);
	}
}
