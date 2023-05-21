/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12951
 * JadenCase 문자열 만들기
 * 텍스트 다루기
 */

package programmers.level2;

class Solution12951 {
//	public String solution(String s) {
//		StringBuilder sb = new StringBuilder();
//
//		for (int i=0; i<s.length(); i++) {
//			char c = s.charAt(i);
//			char prevC = 0;
//			if (i-1 > 0)
//				prevC = s.charAt(i-1);
//			if (c == ' ' && prevC == ' ')
//				c = '*';
//			c = Character.toLowerCase(c);
//			sb.append(c);
//		}
//		
//		String[] arr = sb.toString().split(" ");
//		sb = new StringBuilder();
//		
//		for (int i=0; i<arr.length; i++) {
//			String chunk = arr[i];
//			chunk = Character.toUpperCase(chunk.charAt(0)) + chunk.substring(1);
//			chunk = chunk.replace('*', ' ');
//			
//			sb.append(chunk);
//			if (i != arr.length-1)
//				sb.append(" ");
//		}
//		
//		return sb.toString();
//	}
	public String solution(String s) {
		boolean isStart = true;
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			char prevC = 0;
			if (i-1 > 0)
				prevC = s.charAt(i-1);
			
			if (isStart) {
				c = Character.toUpperCase(c);
			} else {
				c = Character.toLowerCase(c);
			}
			sb.append(c);
			
			if (c == ' ') {
				isStart = true;
			} else {
				isStart = false;
			}
		}
		
		return sb.toString();
	}
//    public String solution(String s) {
//        String answer = "";
//        String[] arr = s.toLowerCase().split("");
//        boolean isTrue = true;
//        
//        for(String i : arr){
//            answer += isTrue ? i.toUpperCase() : i;
//            isTrue = i.equals(" ") ? true : false;
//        }
//        return answer;
//    }
}

public class JadenCase12951 {

}