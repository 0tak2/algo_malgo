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
	public int compareTo(Job o) {
		if (duration > o.duration)
			return 1;
		else if (duration < o.duration)
			return -1;
		return 0;
	}
}

class Solution42626 {
//    public int solution(int[][] jobs) {
//        PriorityQueue<Job> pq = new PriorityQueue<>();
//        for (int[] j : jobs) {
//        	pq.offer(new Job(j[0], j[1]));
//        }
//        
//        int current = 0;
//        int elapsed = 0;
//        while(!pq.isEmpty()) {
//        	Job job = pq.poll();
//        	current += job.duration;
//        	
//        	elapsed += current - job.req;
//        }
//        
//        return elapsed/jobs.length;
//    }
	public int solution(int[][] jobs) {
		PriorityQueue<Job> timeq = new PriorityQueue<Job>((Job o1, Job o2) -> {
			if (o1.req > o2.req)
				return 1;
			else if (o1.req < o2.req)
				return -1;
			return 0;
		});
		PriorityQueue<Job> durq = new PriorityQueue<>();

		for (int[] j : jobs) {
			timeq.offer(new Job(j[0], j[1]));
		}

		int time = 0;
		while (true) {
			timeq.poll()
		}

		return elapsed / jobs.length;
	}
}

public class DiskController42627 {

}
