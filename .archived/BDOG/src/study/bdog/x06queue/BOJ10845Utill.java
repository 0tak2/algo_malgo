package study.bdog.x06queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10845Utill {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Queue<Integer> q = new LinkedList<>();
		
		int total = Integer.parseInt(br.readLine());
		
		for (int i=0; i<total; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int arg = -1;
			if (st.hasMoreTokens()) arg = Integer.parseInt(st.nextToken());
			
			switch (cmd) {
			case "push":
				q.add(arg);
				break;
			case "pop":
				Integer popped = q.poll();
				if (popped != null) {
					System.out.println(popped);
				} else {
					System.out.println(-1);
				}
				break;
			case "size":
				System.out.println(q.size());
				break;
			case "empty":
				if (q.size() == 0) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case "front":
				if (q.size() == 0) {
					System.out.println(-1);
				} else {
					System.out.println(q.peek());
				}
				break;
			case "back":
				if (q.size() == 0) {
					System.out.println(-1);
				} else {
					System.out.println(((LinkedList<Integer>) q).get(q.size() - 1));
				}
				break;
			default:
				break;
			}
		}
	}
}
