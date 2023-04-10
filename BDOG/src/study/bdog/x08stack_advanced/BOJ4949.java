package study.bdog.x08stack_advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ4949 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		Stack<Character> stack = null;
		
		while((line = br.readLine()) != null) {
			boolean checked = true;
			stack = new Stack<>();
			
			if (line.equals(".")) break;
			
			for (int i=0; i<line.length(); i++) {
				char ch = line.charAt(i);
				if (ch == '(' || ch == '[') {
					stack.push(ch);
				} else if (ch == ')') {
					if (stack.empty() || stack.lastElement() != '(') {
						checked = false;
						break;
					}
					stack.pop();
				} else if (ch == ']') {
					if (stack.empty() || stack.lastElement() != '[') {
						checked = false;
						break;
					}
					stack.pop();
				}
			}
			
			if (!stack.empty()) checked = false;
			
			if (checked) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}
}
