/**
 * 3진법 뒤집기
 * https://school.programmers.co.kr/learn/courses/30/lessons/68935
 */

package programmers.level1;

class Solution68935 {
	/*
 	public int solution(int n) {        
        String a = Integer.toString(n, 3);
        StringBuilder sb = new StringBuilder(a);
        String b = sb.reverse().toString();
        
        return Integer.parseInt(b, 3);
    }
	 * */
	
    public int solution(int n) {
        String samJin = sibJinToSamJin(n);
        StringBuilder sb = new StringBuilder(samJin);
        
        return samJinToSibJin(sb.reverse().toString());
    }
    
    String sibJinToSamJin(int n) {
    	StringBuilder sb = new StringBuilder();
    	
    	while (n >= 3) {
    		int remain = n % 3;
    		sb.insert(0, remain);
    		n /= 3;
    	}
    	if (n != 0) {
    		sb.insert(0, n);
    	}
    	
    	return sb.toString();
    }
    
    int samJinToSibJin(String nStr) { // Integer.parseInt(a,3); 띠용
    	String[] nStrArr = nStr.split("");
    	
    	int sum = 0;
    	for (int i=0; i<nStrArr.length; i++) {
    		int jegobsu = nStrArr.length - 1 - i;
    		sum += Integer.parseInt(nStrArr[i]) * Math.pow(3, jegobsu);
    	}
    	
    	return sum;
    }
}

public class Reverse3JinBeob68935 {
	public static void main(String[] args) {
		Solution68935 solution = new Solution68935();
		String test = solution.sibJinToSamJin(11);
		System.out.println(test);
	}
}
