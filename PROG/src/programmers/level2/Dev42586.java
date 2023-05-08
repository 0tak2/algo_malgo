/**
 * 기능 개발
 * https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * 
 * 다른 사람들의 풀이를 보니... 허허...
 * 내머리는똥이야
 */
package programmers.level2;

import java.util.*;

class Node {
	private int prog;
	private int speed;

	public Node(int prog, int speed) {
		this.prog = prog;
		this.speed = speed;
	}

	public int getProg() {
		return prog;
	}

	public int getSpeed() {
		return speed;
	}
}

class Dev42586 {
	public int[] solution(int[] progresses, int[] speeds) {
		int dayCount = 0; // 경과 일자를 표현한다. 0에서 시작한다.
		Queue<Node> q = new LinkedList<>(); // 각 기능을 담을 큐. 배포대기큐
		Map<Integer, Integer> m = new TreeMap<>(); // 일자별 배포 기능의 개수를 표현하는 맵. 알아서 정렬되도록 TreeMap 사용.

		// 각 기능을 Node VO에 담아 큐에 넣는다
		for (int i = 0; i < progresses.length; i++) {
			Node n = new Node(progresses[i], speeds[i]);
			q.offer(n);
		}

		// 큐가 빌 때까지 (모든 기능을 배포할 때까지) 반복
		while (!q.isEmpty()) {
			// 가장 앞에 있는 항목을 조사
			Node n = q.peek();
			
			// 배포까지 남은 일자를 계산
			int remainDays = (int) Math.ceil((double) (100 - n.getProg()) / n.getSpeed());
			
			// 남은 일자가 현재 경과 일자보다 크다면
			if (remainDays > dayCount) {
				dayCount = remainDays; // 현재 경과 일자를 남은 일자로 조정한다.
			}
			// 바꿔말하면, 남은 일자가 현재 경과 일자보다 크지 않은 경우,
			// 현재 경과 일자를 그대로 유지한다.
			
			// 현재 경과 일자에 대한 배포 횟수를 업데이트한다.
			m.put(dayCount, m.getOrDefault(dayCount, 0) + 1);
			
			// 조사한 항목을 큐에서 제외한다.
			q.poll();
		}

		// for (Map.Entry<Integer, Integer> e : m.entrySet()) {
		// System.out.println(e.getKey());
		// System.out.println(e.getValue());
		// System.out.println();
		// }

		// 배열로 변환
		int[] answer = new int[m.entrySet().size()];
		int idx = 0;
		for (Map.Entry<Integer, Integer> e : m.entrySet()) {
			answer[idx] = e.getValue();
			idx++;
		}

		return answer;
	}
}