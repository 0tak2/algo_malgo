package programmers.test.level1;

public class Test1 {
    public String solution(String s, int n) {
        String answer = "";
        
        StringBuffer sb = new StringBuffer();
        
        for (int i=0; i<s.length(); i++) {
        	char ch = s.charAt(i);
        	
        	if (ch == ' ') sb.append(ch);
        	else if (ch >= 'a' && ch <= 'z') {
        		if (ch + n <= 'z') {
        			sb.append((char)(ch + n));
        		} else {
        			sb.append((char)(ch + n - 'z' + 'a' - 1));
        		}
        	} else if (ch >= 'A' && ch <= 'Z') {
        		if (ch + n <= 'Z') {
        			sb.append((char)(ch + n));
        		} else {
        			sb.append((char)(ch + n - 'Z' + 'A' - 1));
        		}
        	}
        }
        
        answer = sb.toString();
        
        return answer;
    }
}