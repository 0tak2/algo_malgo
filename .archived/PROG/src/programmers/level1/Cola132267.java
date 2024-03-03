// 스킵
package programmers.level1;

class Solution132267 {
	int got = 0;
	int remain = 0;
	int a = 0;
	
    public int solution(int a, int b, int n) {
        int answer = 0; // 바꿔먹은 콜라 수
        
        remain = n; // 현재 가진 빈 병 수
        this.a = a;
        while(remain > a) {
        	calc();
        }
        
        return got;
    }
    
    public void calc() {
    	got += remain / a;
    	remain = remain - (remain / a) * a + remain % a;
    }
}

public class Cola132267 {

}
