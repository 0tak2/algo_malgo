/**
 * 같은 숫자는 싫어
 * https://school.programmers.co.kr/learn/courses/30/lessons/12906
 * 
 * 덱을 사용하였다.
 */

package programmers.level1;

import java.util.*;

public class NoSameNumber12906 {
    public int[] solution(int []arr) {      
        Deque<Integer> dq = new LinkedList<>();
        
        for (int i : arr) {
            if (dq.peekLast() != null && dq.peekLast() == i)
                continue;
            dq.offerLast(i);
        }
        
        int[] answer = ((List<Integer>)dq).stream()
            .mapToInt(i -> i)
            .toArray();
        return answer;
    }
}