/*
 * 의상
 * https://school.programmers.co.kr/learn/courses/30/lessons/42578
 * */
package programmers.level2;

import java.util.*;

/*
 * 의상의 종류별 집합을 A, B, C, ... 라고 하고,
 * 특정 종류에 해당하는 전체 의상의 수를 n(K)라고 하자. (K는 의상 종류)
 * 그럼 정답은,
 * answer = {n(A) + 1} + {n(B) + 1} + {n(C) + 1} + ... - 1
 * 
 * 각 종류 별 하나의 의상을 선택하거나[n(K] 선택하지 않을 수 있으나[1],
 * 모든 의상을 선택하는 경우는 제외해야하기 때문이다.
 */

class Clothes42578 {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            String name = cloth[0];
            String cat = cloth[1];
            
            int count = map.getOrDefault(cat, 0);
            
            map.put(cat, count+1);
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            answer *= entry.getValue() + 1;
        }
        
        return answer-1; // 1을 뺀다.
    }
}