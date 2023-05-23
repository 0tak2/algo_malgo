package programmers.level3;

class Solution43162 {
	int[][] shouldVisit;
	int answer = 0;
	
	void dfs(int host) {
		int visitCnt = 0;
		for (int i=0; i<shouldVisit.length; i++) {
			if (shouldVisit[host][i] == 1) {
				shouldVisit[host][i] = 0;
				dfs(i);
				visitCnt++;
			}
		}
		
		if (visitCnt == 0) {
			answer++;
		}
	}
	
    public int solution(int n, int[][] computers) {
        this.shouldVisit = computers;
        
        dfs(0);
        
        return answer;
    }
}

public class Network43162 {

}
