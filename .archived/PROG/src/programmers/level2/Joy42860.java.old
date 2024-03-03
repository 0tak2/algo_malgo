package programmers.level2;

class Solution42860 {
    public int solution(String name) {
        int answer = 0;
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<name.length(); i++) {
        	sb.append('A');
        }
        
        int cursor = 0;
        while(sb.toString().equals(name)) {
        	char target = name.charAt(cursor);
        	char current = sb.charAt(cursor);
        	if (target != sb.charAt(cursor)) {
        		// 알파벳 바꾸기
        		int diffA = target - current;
        		if (diffA < 0) {
        			diffA = target - 'A' + 'Z' - current + 1;
        		}
        		
        		int diffB = current - target;
        		if (diffB < 0) {
        			diffB = target - 'A' + 'Z' - current + 1;
        		}
        		
        		int diff = Math.min(diffA, diffB);
        		answer += diff;
        		sb.setCharAt(cursor, target);
        	}
        	
        	// 자리 옮기기
        	int ll = 9999;
        	int lr = 9999;
        	int rl = 9999;
        	int rr = 9999;
        	
        	String strL = sb.substring(0, cursor);
        	String strR = sb.substring(cursor+1);
        	
        	if (!"".equals(strL)) {
        		ll = strL.indexOf('A');
        		rl = strL.lastIndexOf('A');
        	}
        	
        	if (!"".equals(strR)) {
        		rl = strR.indexOf('A');
        		rr = strR.lastIndexOf('A');
        	}
        	
        	int llDistance = sb.length() - 1
        }
        
        return answer;
    }
}

public class Joy42860 {

	public static void main(String[] args) {
		Solution42860 solution = new Solution42860();
	}
}
