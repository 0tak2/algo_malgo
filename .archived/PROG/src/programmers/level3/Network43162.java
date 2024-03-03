/**https://school.programmers.co.kr/learn/courses/30/lessons/43162
 * 네트워크
 * 
 * DFS, 노드의 연결 개수 세기
 * DFS... 너무 어렵다...
 */
package programmers.level3;

class Solution43162 {
	int[][] computers;
	boolean[] isUsed;
	int answer = 0;
	
	void dfs(int host) {
       isUsed[host] = true;
		for (int i=0; i<computers[host].length; i++) {
			if (!isUsed[i] && computers[host][i] == 1) {
				dfs(i);
			}
		}
	}
	
    public int solution(int n, int[][] computers) {
        this.computers = computers;
        this.isUsed = new boolean[computers.length];
        
        for(int i=0; i<n; i++) {
        	if(!isUsed[i]) {
        		dfs(i);
        		answer++;
        	}
        }
        
        return answer;
    }
}

public class Network43162 {
	public static void main(String[] args) {
		
	}
}
