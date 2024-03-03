/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 * H-Index
 * 
 * 아직 못풀었음
 */
package programmers.level2;

import java.util.Arrays;
import java.util.Collections;

class Solution42747 {
    public int solution(int[] citationsRaw) {
        int answer = 0;
        
		Integer citations[] = Arrays.stream(citationsRaw).boxed().toArray(Integer[]::new); 
        
        Arrays.sort(citations, Collections.reverseOrder());
        
        for (int h : citations) {
        	int overH = 0;
        	boolean[] remain = new boolean[citations.length];
        	for (int idx=0; idx<citations.length; idx++) {
        		int c = citations[idx];
        		if (c >= h) {
        			overH++;
        			remain[idx] = true;
        		}
        	}
        	
        	if (overH < h) {
        		continue;
        	}
        	
//        	for (boolean visited : )
        }
        
        return answer;
    }
}

public class Hindex42747 {

}
