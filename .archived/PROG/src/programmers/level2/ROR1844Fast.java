/**
 * 게임 맵 최단거리 (빠른 풀이)
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 * 
 * BFS
 * 
 * BFS로 특정 지점을 탐색하면 그 탐색 경로가 최단 경로이라는 것은 자명하다.
 * https://nulls.co.kr/graph/141
 * https://codermun-log.tistory.com/m/294
 */
package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

class Solution1844Fast {
	public int solution(int[][] maps) {
		int distance = bfs(new Node1844Fast(0, 0), maps);
		return distance;
	}
	
	public int bfs(Node1844Fast start, int[][] maps) {	
		int yLast = maps.length-1;
		int xLast = maps[yLast].length-1;
		
		boolean[][] visited = new boolean[yLast+1][xLast+1];
		int[][] distances = new int[yLast+1][xLast+1]; // 출발점으로부터의 거리
		int answerDistance = -1;
		
		Queue<Node1844Fast> q = new LinkedList<>();
		q.offer(start);
		while(!q.isEmpty()) {
			Node1844Fast node = q.poll();
			int y = node.y;
			int x = node.x;
//			visited[y][x] = true; // 왜인지 모르게 여기서 방문 체크를 하면 효율성 검사를 통과하지 못함. 36, 43, 50, 57
			
			if (y == yLast && x == xLast) {
				answerDistance = distances[y][x] + 1;
				break;
			}
			
			// 상 y-1
			if (y-1 >= 0 && maps[y-1][x] == 1 && !visited[y-1][x]) {
				distances[y-1][x] = distances[y][x] + 1;
				visited[y-1][x] = true;
				q.offer(new Node1844Fast(y-1, x));
			}
			
			// 하 y+1
			if (y+1 <= yLast && maps[y+1][x] == 1 && !visited[y+1][x]) {
				distances[y+1][x] = distances[y][x] + 1;
				visited[y+1][x] = true;
				q.offer(new Node1844Fast(y+1, x));
			}
			
			// 좌 x-1
			if (x-1 >= 0 && maps[y][x-1] == 1 && !visited[y][x-1]) {
				distances[y][x-1] = distances[y][x] + 1;
				visited[y][x-1] = true;
				q.offer(new Node1844Fast(y, x-1));
			}
			
			// 우 x+1
			if (x+1 <= xLast && maps[y][x+1] == 1 && !visited[y][x+1]) {
				distances[y][x+1] = distances[y][x] + 1;
				visited[y][x+1] = true;
				q.offer(new Node1844Fast(y, x+1));
			}
		}
		
		return answerDistance;
	}
}

class Node1844Fast {
	int y;
	int x;
	
	public Node1844Fast(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class ROR1844Fast {
	public static void main(String[] args) {
		Solution1844Fast solution = new Solution1844Fast();
		int ans1 = solution.solution(new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}});
		System.out.println(ans1);
	}
}
