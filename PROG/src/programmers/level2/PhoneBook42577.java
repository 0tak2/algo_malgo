package programmers.level2;

import java.util.*;

class PhoneBook42577 {
	public boolean solution(String[] phone_book) {
	    Map<String, Integer> map = new HashMap<>();
	    
	    // 우선 주어진 전화번호를 모두 맵에 추가한다.
	    for (String phone : phone_book) {
	        map.put(phone, 1);
	    }
	    
	    // 다시 한 번 전화번호를 순회한다
	    for (String phone : phone_book) {
	        for (int i=1; i<phone.length(); i++) {
	        	// 각 전화번호별로 한 글자씩 부분문자열을 늘려가면서
	        	// 맵에 있는지 확인한다.
	            
	            // 전체 문자열 자체가 대응되는 경우는 제외해야 자신을 비교하지 않음
	            // (i가 phone.length()인 경우 제외)
	            // System.out.println(map.get(phone.substring(0, i)));
	            // System.out.println(phone.substring(0, i));
	            // System.out.println();
	            String cmp = phone.substring(0, i);
	            if (map.get(cmp) != null) {
	                // System.out.println("correct");
	                return false;
	            }
	        }
	    }
        
	    return true;
	}
}

/*
정확성  테스트
테스트 1 〉	통과 (0.02ms, 75MB)
테스트 2 〉	통과 (0.03ms, 77.8MB)
테스트 3 〉	통과 (0.04ms, 78.7MB)
테스트 4 〉	통과 (0.03ms, 72.4MB)
테스트 5 〉	통과 (0.04ms, 75.9MB)
테스트 6 〉	통과 (0.05ms, 80.6MB)
테스트 7 〉	통과 (0.04ms, 67.4MB)
테스트 8 〉	통과 (0.02ms, 76.6MB)
테스트 9 〉	통과 (0.03ms, 71.5MB)
테스트 10 〉	통과 (0.04ms, 76.5MB)
테스트 11 〉	통과 (0.03ms, 71.7MB)
테스트 12 〉	통과 (0.04ms, 75.7MB)
테스트 13 〉	통과 (0.05ms, 71.6MB)
테스트 14 〉	통과 (1.69ms, 81.3MB)
테스트 15 〉	통과 (1.81ms, 87.3MB)
테스트 16 〉	통과 (6.90ms, 82.2MB)
테스트 17 〉	통과 (5.97ms, 79.8MB)
테스트 18 〉	통과 (8.72ms, 77.9MB)
테스트 19 〉	통과 (4.45ms, 80.6MB)
테스트 20 〉	통과 (8.08ms, 89.5MB)

효율성  테스트
테스트 1 〉	통과 (3.61ms, 57.9MB)
테스트 2 〉	통과 (5.89ms, 56.6MB)
테스트 3 〉	통과 (273.60ms, 210MB)
테스트 4 〉	통과 (182.60ms, 137MB)
*/

/*
 *
 * 다른 풀이에서 String.startWith(String prefix)를 이용하여 쉽게 해결하고 있다.
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#startsWith(java.lang.String)
 * */