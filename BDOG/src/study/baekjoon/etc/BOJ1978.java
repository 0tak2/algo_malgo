package study.baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1978 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(br.readLine());
		
		int[] arr = new int[total];
		String line = br.readLine();
		
		StringTokenizer st = new StringTokenizer(line);
		for (int i=0; i<total; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int lastNum = arr[arr.length-1];
		
		int ans = 0;
		
		for (int i=2; i<=lastNum; i++) {
			if (isPrime(i)) {
				for (int target : arr) {
					if (i == target) ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	private static boolean isPrime(int num) {
		if (num < 2) return false;
		
		for (int i=2; i<=Math.sqrt(num); i++) {
			if (num % i == 0) return false;
		}
		
		return true;
		
	}
}
