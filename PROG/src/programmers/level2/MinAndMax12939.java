package programmers.level2;

class Solution12939 {
    public String solution(String s) {
        String answer;
        
        // 다른 예제를 보니 그냥 배열의 첫 값을 초기 값으로 잡는다.
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        
        String[] sArr = s.split(" ");
        for (String numStr : sArr) {
        	long cur = Long.parseLong(numStr);
        	
        	if (cur < min) min = cur;
        	if (cur > max) max = cur;
        }
        
        answer = String.valueOf(min) + " " + String.valueOf(max); 
        
        return answer;
    }
}
// 다른 풀이: Arrays.sort()로 정렬 먼저

public class MinAndMax12939 {

}
