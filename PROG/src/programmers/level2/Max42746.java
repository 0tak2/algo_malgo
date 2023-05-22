package programmers.level2;

import java.util.Arrays;

class Solution42746 {
	public String solution(int[] numbersInput) {
		String answer = "";
		
		Integer numbers[] = Arrays.stream(numbersInput).boxed().toArray(Integer[]::new); 
		
		Arrays.sort(numbers, (o1, o2) -> {
//			char o1Head = String.valueOf(o1).charAt(0);
//			char o2Head = String.valueOf(o2).charAt(0);
//			
//			return o2Head - o1Head;
			
			String o1Str = String.valueOf(o1);
			String o2Str = String.valueOf(o2);
			int idx = 0;
			int cmp = 0;
			while (idx < o1Str.length()-1 && idx < o2Str.length()-1) {
				char c1 = o1Str.charAt(idx);
				char c2 = o2Str.charAt(idx);
				
				int tmp = c1 - c2;
				if (tmp != 0) {
					cmp = tmp;
					break; // 에라이모루겟다
				}
			}
				
			
			return 0;
		});
		
		for (int num : numbers) {
			answer += String.valueOf(num);
		}

		return answer;
	}
}

public class Max42746 {

}
