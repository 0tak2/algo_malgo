package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ11723 {
	static StringBuilder sb;
	static Set<Integer> set;
	
	static void add(int target) {
		set.add(target);
	}
	
	static void remove(int target) {
		set.remove(target);
	}
	
	static void check(int target) {
		if (set.contains(target)) {
			sb.append("1\n");
		} else {
			sb.append("0\n");
		}
	}
	
	static void toggle(int target) {
		if (!set.contains(target)) {
			add(target);
		} else {
			remove(target);
		}
	}
	
	static void all() {
		Integer[] allArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		set = new HashSet<Integer>(Arrays.asList(allArr));
	}
	
	static void empty() {
		set.clear();
	}
	
	static void operation(String op, int target) {
		switch (op) {
		case "add":
			add(target);
			break;
		case "remove":
			remove(target);
			break;
		case "check":
			check(target);
			break;
		case "toggle":
			toggle(target);
			break;
		case "all":
			all();
			break;
		case "empty":
			empty();
			break;
		default:
			break;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int numOfTask = Integer.parseInt(br.readLine());
		
		set = new HashSet<>(numOfTask);
		sb = new StringBuilder();
		
		for (int i=0; i<numOfTask; i++) {
			String[] input = br.readLine().split(" ");
			String op = input[0];
			int target = input.length > 1 ? Integer.parseInt(input[1]) : -1;
			operation(op, target);
		}
		
		System.out.println(sb.toString().trim());
	}
}
