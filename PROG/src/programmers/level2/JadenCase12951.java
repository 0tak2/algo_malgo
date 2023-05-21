package programmers.level2;

class Solution12951 {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        String[] chunks = s.split(" ");
        for (String chunk : chunks) {
        	StringBuilder innerSb = new StringBuilder();
        	
        	for (int i=0; i<chunk.length(); i++) {
        		char c = chunk.charAt(i);
        		if (i==0 && (c >= 'a' && c <= 'z')) {
        			c -= 32;
        		} else if (i>0 && (c >= 'A' && c <= 'Z')) {
        			c += 32;
        		}
        		innerSb.append(c);
        	}
        	
        	sb.append(innerSb.toString());
    		sb.append(" ");
        }
        
        return sb.substring(0, sb.length()-1).toString(); // " "로 split하지 않고 그냥 for문으로 글자별로 순회하기
    }
}

public class JadenCase12951 {

}
