package study.bdog.x05stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Stack {
	int[] data = new int[10000];
	int pos = 0;
	
	public Stack() {
	}
	
	public void push(int num) {
		data[pos++] = num;
	}
	
	public int pop() {
		if (this.size() <= 0) return -1;
		
		return data[pos-- - 1];
	}
	
	public int top() {
		if (this.size() <= 0) return -1;
		
		return data[pos-1];
	}
	
	public int size() {
		return pos;
	}
	
	public int empty() {
		return pos == 0 ? 1 : 0;
	}
}

public class BOJ10828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int count = Integer.parseInt(br.readLine());
		
		Stack stack = new Stack();
		
		for(int i=0; i < count; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			int arg = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
			if(cmd.equals("push")) stack.push(arg);
			else if(cmd.equals("pop")) System.out.println(stack.pop());
			else if(cmd.equals("size")) System.out.println(stack.size());
			else if(cmd.equals("empty")) System.out.println(stack.empty());
			else if(cmd.equals("top")) System.out.println(stack.top());
		}
	}
}
