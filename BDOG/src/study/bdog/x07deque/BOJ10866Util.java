package study.bdog.x07deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ10866Util {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Deque<Integer> dq = new LinkedList<>();
		
		int total = Integer.parseInt(br.readLine());
		
		for (int i=0; i<total; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int arg = -1;
			if (st.hasMoreTokens()) arg = Integer.parseInt(st.nextToken());
			
			switch (cmd) {
			case "push_front":
				dq.addFirst(arg);
				break;
				
			case "push_back":
				dq.addLast(arg);
				break;
				
			case "pop_front":
				Integer popped = dq.pollFirst();
				if (popped != null) {
					System.out.println(popped);
				} else {
					System.out.println(-1);
				}
				break;
				
			case "pop_back":
				Integer poppedBack = dq.pollLast();
				if (poppedBack != null) {
					System.out.println(poppedBack);
				} else {
					System.out.println(-1);
				}
				break;
				
			case "size":
				System.out.println(dq.size());
				break;
				
			case "empty":
				if (dq.size() == 0) System.out.println(1);
				else System.out.println(0);
				break;
				
			case "front":
				if (dq.size() == 0) System.out.println(-1);
				else System.out.println(dq.getFirst());
				break;
				
			case "back":
				if (dq.size() == 0) System.out.println(-1);
				else System.out.println(dq.getLast());
				break;
			
			default:
				break;
			}
		}
	}
}
