package study.bdog.x05stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class BOJ10828Util {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int count = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i < count; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			int arg = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
			if(cmd.equals("push")) stack.push(arg);
			else if(cmd.equals("pop")) {
				if (stack.size() == 0) {
					System.out.println(-1);
				} else {
					System.out.println(stack.pop());
				}
			} else if(cmd.equals("size"))
				System.out.println(stack.size());
			else if(cmd.equals("empty")) {
				if (stack.empty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			}
			else if(cmd.equals("top")) {
				if (stack.size() == 0) {
					System.out.println(-1);
				} else {
					System.out.println(stack.peek());
				}
			}
		}
	}
}
