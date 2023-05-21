/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/77484
 */
package programmers.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution77484 {
	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = {};
		
		Map<Integer, Boolean> lottoMap = new HashMap<>();
		
		for (int win : win_nums) {
			lottoMap.put(win, false);
		}
		
		Arrays.sort(lottos);
		
		for (int mine : lottos) {
			
		}

		return answer;
	}
}

public class Lotto77484 {

}
