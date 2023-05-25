package programmers.level2;

class Solution1844 {
	int answer = 0;
	int[][] maps;
	boolean[][] visited;
	
	void dfs(int y, int x) {
		// 나가기
		// 벽
		if (visited[y][x]) return;
		// 목적지 도착
		if (y==maps.length-1 && x==maps.length-1) {
			
		}
		
		// 수행하기
		visited[y][x] = true;
		
		// 오른쪽 / 아래 >> 왼쪽 / 위
		
		// 오른쪽
		if(x+1 < maps.length && maps[y][x+1] == 1) dfs(y, x+1);
		// 왼쪽
		if(x-1 >= 0 && maps[y][x-1] == 1) dfs(y, x-1);
		// 위
		if(y-1 >= 0 && maps[y-1][x] == 1) dfs(y-1, x);
		// 아래
		if(y+1 < maps.length && maps[y+1][x] == 1) dfs(y+1, x);
	}
	
    public int solution(int[][] maps) {
        this.maps = maps;
        this.visited = new boolean[maps.length][maps.length];
        
        dfs(0, 0);
        
        return answer;
    }
}

public class Shortest1844 {
	
}