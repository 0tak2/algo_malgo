/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/72410
 * 신규 아이디 추천
 * 
 * 자바에서 정규식 사용
 * https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%95%EA%B7%9C%EC%8B%9DRegular-Expression-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%A0%95%EB%A6%AC
 */
package programmers.level1;

class Solution72410 {
    public String solution(String new_id) {
        // 1단계
        new_id = new_id.toLowerCase();
        
        // 2단계
        new_id = new_id.replaceAll("[^a-z|0-9|\\-|\\_|\\.]", ""); // temp = temp.replaceAll("[^-_.a-z0-9]","");
        
        // 3단계
        new_id = new_id.replaceAll("\\.{2,}", "."); // temp = temp.replaceAll("[.]{2,}",".");
        
        // 4단계
        new_id = new_id.replaceAll("^\\.|\\.$", ""); // temp = temp.replaceAll("^[.]|[.]$","");
        
        // 5단계
        new_id = new_id.isEmpty() ? "a" : new_id;
        
        // 6단계
        if (new_id.length() >= 16) {
        	new_id = new_id.substring(0, 15);
        	if(new_id.charAt(14) == '.')
        		new_id = new_id.substring(0, 14);
        }
        
        // 7단계
        while (new_id.length() <= 2) {
        	new_id = new_id + new_id.charAt(new_id.length()-1);
        }
        
        return new_id;
    }
}

public class NewId72410 {

}
