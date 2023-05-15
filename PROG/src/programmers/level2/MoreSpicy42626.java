package programmers.level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class Solution42626 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        for (int i : scoville) {
        	heap.add(i);
        }
        
        while (heap.peek() < K) {
        	 if (heap.size() <= 1) {
        		 answer = -1;
        		 break;
        	 }
        	 
        	 int a = heap.poll();
        	 int b = heap.poll();
        	 
        	 heap.add(a + b * 2);
        	 answer++;
        }
        
        return answer;
    }
}

public class MoreSpicy42626 {

	public static void main(String[] args) {
		Solution42626 solution = new Solution42626();
		System.out.println(solution.solution(new int[] {1, 2, 3, 9, 10, 12}, 7));
	}
}
