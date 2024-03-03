/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42627
 * 디스크 컨트롤러
 * 
 * 힙, 우선순위큐, Comparator, Comparable
 */
package programmers.level3;

import java.util.Comparator;
import java.util.PriorityQueue;

class Job implements Comparable<Job> {
	int req; // 요청 시점
	int duration; // 소요 시간

	Job(int req, int duration) {
		this.req = req;
		this.duration = duration;
	}

	@Override
	public int compareTo(Job o) { // 소요 시간을 기준으로 잡았다.
		if (duration > o.duration) // 현재 객체를 기준으로, 현재 객체가 비교 객체보다 크면 양수 반환
			return 1;
		else if (duration < o.duration)// 현재 객체를 기준으로, 현재 객체가 비교 객체보다 작으면 음수 반환
			return -1;
		return 0; // 같으면 0 반환
	}
}

class Solution42626 {
	public int solution(int[][] jobs) {
		PriorityQueue<Job> timeq = new PriorityQueue<Job>((Job o1, Job o2) -> { // Comparator 인터페이스를 람다로 구현하여 넘김
			if (o1.req > o2.req) // 요청 시간을 순서로 함. 1번 값이 기준 값이 됨.
				return 1; // 1번 값과 2번 값을 비교하여 1번이 크면 양수 반환
			else if (o1.req < o2.req) // 1번이 작으면 음수 반환
				return -1;
			return 0; // 같으면 0 반환
		});

		for (int[] j : jobs) {
			timeq.offer(new Job(j[0], j[1]));
		}

		int time = timeq.peek().req; // 현재 시간. 가장 먼저 들어온 값의 요청 시간에서 시작
		int elapsed = 0; // 경과 시간
		while (!timeq.isEmpty()) {
			PriorityQueue<Job> durq = new PriorityQueue<>();
			for (Job job : timeq) {
				if (job.req > time) continue; // 현재 시간에서 실행할 수 없는 것을 제외하고
				durq.offer(job); // 우선순위 순으로 정렬된 현재 작업 큐에 넣는다.
			}
			Job exe = durq.poll(); // 작업 큐에서 가장 앞에 있는 (우선순위가 가장 높은) 값을 꺼낸다
			if (exe == null) { // 없는 경우,
				durq.offer(timeq.poll()); // 시간을 뒤로 옮겨서 실행할 수 있는 값이 있다면
				exe = durq.poll(); // 그것을 실행하도록 선택한다.
				time = exe.req;
				if (exe == null) break; // 없으면 루프에서 벗어난다.
			}
			time += exe.duration; // 현재 시간을 작업이 종료된 시점으로 옮긴다.
			elapsed += time - exe.req; // 누적 경과 시간에 현재 작업 소요 시간을 더한다.
			timeq.remove(exe); // 전체 큐에서 실행한 작업을 제거한다.
		}

		return elapsed / jobs.length; // 평균 시간을 반환한다.
	}
}

public class DiskController42627 {

	public static void main(String[] args) {
		Solution42626 solution = new Solution42626();
		System.out.println(solution.solution(new int[][] {{0, 10}, {4, 10}, {15, 2}, {5, 11}}));
	}
}
