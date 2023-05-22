/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42587
 * 프로세스
 * 
 * 큐
 */
package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

class Process {
	public int id;
	public int pr;
	public Process(int id, int pr) {
		this.id = id;
		this.pr = pr;
	}
}

class Solution425878 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Process> q = new LinkedList<>();
        
        for (int i=0; i<priorities.length; i++) {
        	int pr = priorities[i];
        	int id = i;
        	
        	q.offer(new Process(id, pr));
        }
        
        int cnt = 0;
        Main: while(!q.isEmpty()) {
        	Process cur = q.poll();
        	for (Process p : (LinkedList<Process>)q) {
        		if (p.pr > cur.pr) {
        			q.offer(cur);
        			continue Main;
        		}
        	}
        	cnt++;
        	if (location == cur.id) {
        		answer = cnt;
        		break Main;
        	}
        }
        
        return answer;
    }
}

public class Process42587 {

}
