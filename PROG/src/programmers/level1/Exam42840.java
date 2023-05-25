/**
 * 모의고사
 * https://school.programmers.co.kr/learn/courses/30/lessons/42840
 * 
 * 완전탐색
 */

/*
 * 아래처럼
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        패턴을 지정하는 풀이가 많았다. 이렇게 하는 편이 더 좋아보인다.
 */


package programmers.level1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution42840 {
	public int[] solution(int[] answers) {
		List<Integer> answer = new ArrayList<>();
		
		Map<Integer, Integer> scores = new HashMap<>();
		
		int[] ss = new int[3];
		ss[0] = supo1(answers);
		ss[1] = supo2(answers);
		ss[2] = supo3(answers);
		
		int max = ss[0];
		for (int i=0; i<3; i++) {
			if (ss[i] > max) max = ss[i];
			scores.put(i+1, ss[i]);
		}
		
		for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
			if (entry.getValue() == max) {
				answer.add(entry.getKey());
			}
		}
		
		return answer.stream()
                .mapToInt(i -> i)
                .toArray();
	}
	
	int supo1(int[] answers) {
		// 0 1 2 3 4 | 5 6 7 8 9 | 10
		// 1 2 3 4 5 | 1 2 3 4 5 | 1
		// 5로 나눈 나머지
		int cnt = 0;
		
		for (int i=0; i<answers.length; i++) {
			int remain = i%5;
			int jjick = remain + 1;
			int cur = answers[i];
			
			if (cur == jjick) cnt++;
		}
		
		return cnt;
	}
	
	int supo2(int[] answers) {
		// 0 1 2 3 4 5 6 7 | 8 9 10 11 12 13 14 15
		// 2 1 2 3 2 4 2 5 | 2 1 2  3  2  4  2  5
		// 8로 나눈 나머지
		
		int cnt = 0;
		
		for (int i=0; i<answers.length; i++) {
			int remain = i%8;
			int jjick;
			if (remain%2 == 0) {
				jjick = 2;
			} else {
				switch (remain) {
				case 1:
					jjick = 1;
					break;
				case 3:
					jjick = 3;
					break;
				case 5:
					jjick = 4;
					break;
				case 7:
					jjick = 5;
					break;
				default:
					jjick = 0;
					break;
				}
			}
			int cur = answers[i];
			
			if (cur == jjick) cnt++;
		}
		
		return cnt;
	}
	
	int supo3(int[] answers) {
		// 0 1 2 3 4 5 6 7 8 9 | 10 11 12
		// 3 3 1 1 2 2 4 4 5 5 | 3  3  1  1 2 2 4 4 5 5 
		// 10으로 나눈 나머지
		
		int cnt = 0;
		
		for (int i=0; i<answers.length; i++) {
			int remain = i%10;
			int jjick;
			switch (remain) {
			case 0:
			case 1:
				jjick = 3;
				break;
			case 2:
			case 3:
				jjick = 1;
				break;
			case 4:
			case 5:
				jjick = 2;
				break;
			case 6:
			case 7:
				jjick = 4;
				break;
			case 8:
			case 9:
				jjick = 5;
				break;
			default:
				jjick = 0;
				break;
			}
			int cur = answers[i];
			
			if (cur == jjick) cnt++;
		}
		
		return cnt;
	}
}

public class Exam42840 {

}
