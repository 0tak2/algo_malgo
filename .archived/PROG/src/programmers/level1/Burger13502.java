package programmers.level1;

import java.util.*;

class Solution135022 {
    public int solution(int[] ingredient) {
        int answer = 0;
        
        StringBuilder sb = new StringBuilder();
        String str = null;
        
        for (int i : ingredient) {
            sb.append(Integer.toString(i));
        }
        
        str = sb.toString();
        while(true) {
            String newStr = str.replaceFirst("1231", "");
            // System.out.println(newStr);
            if (str.equals(newStr)) {
                break;
            }
            str = newStr;
            answer++;
        }
        
        return answer;
    }
}

public class Burger13502 {

	public static void main(String[] args) {
		
	}
}
