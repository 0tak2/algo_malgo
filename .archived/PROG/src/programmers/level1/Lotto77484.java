/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/77484
 * 로또의 최고 순위와 최저 순위
 * 해시맵
 */
package programmers.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution77484 {
	private int countToRank(int count) {
		if (count > 6) count = 6;
		
		switch (count) {
		case 6:
			return 1;
		case 5:
			return 2;
		case 4:
			return 3;
		case 3:
			return 4;
		case 2:
			return 5;
		default:
			return 6;
		}
	}
	
	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		
		int baseCount = 0;
		int zeroCount = 0;
		
		Map<Integer, Boolean> lottoMap = new HashMap<>();
		
		for (int win : win_nums) {
			lottoMap.put(win, false);
		}
		
		Arrays.sort(lottos);
		
		for (int mine : lottos) {
			if (lottoMap.containsKey(mine))
				lottoMap.put(mine, true);
			if (mine == 0) zeroCount++;
		}
		
		for (Map.Entry<Integer, Boolean> entry : lottoMap.entrySet()) {
			if (entry.getValue()) baseCount++;
		}
		
		answer[0] = countToRank(baseCount + zeroCount);
		answer[1] = countToRank(baseCount);

		return answer;
	}
}

public class Lotto77484 {

}
