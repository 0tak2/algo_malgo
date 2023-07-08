/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/118667
 * 두 큐 합 같게 만들기
 * 
 * 합을 쓰는 문제에서 예상과 달리 오답 판정이 되면 long 고려하기
 * 큐
 */
package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

public class TwoQ118667 {
    public long solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();        
        Queue<Integer> q2 = new LinkedList<>();        
        long sum1 = 0;
        long sum2 = 0;
        
        for (int i=0; i<queue1.length; i++) {
        	q1.add(queue1[i]);
        	sum1 += queue1[i];
        	
        	q2.add(queue2[i]);
        	sum2 += queue2[i];
        }
        
        long goal = (sum1 + sum2) / 2;
        
        long cnt = 0;
        while (sum1 != goal || sum2 != goal) {
        	if (q1.isEmpty() || q2.isEmpty()) {
        		cnt = -1;
        		break;
        	}
        	
        	if (cnt >= 300000) {
        		cnt = -1;
        		break;
        	}
        	
        	if (sum1 > sum2) {
        		int trans = q1.poll();
        		q2.add(trans);
        		sum1 -= trans;
        		sum2 += trans;
        	} else {
        		int trans = q2.poll();
        		q1.add(trans);
        		sum1 += trans;
        		sum2 -= trans;
        	}
        	cnt++;
        }
        
        return cnt;
    }
}
