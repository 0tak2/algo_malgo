/**
 * 게임 맵 최단거리 (느린 풀이)
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

class Solution1844 {
	public int solution(int[][] maps) {
		int distance = bfs(new Node1844(0, 0), maps);
		return distance;
	}
	
	public int bfs(Node1844 start, int[][] maps) {	
		int yLast = maps.length-1;
		int xLast = maps[yLast].length-1;
		
		boolean[][] visited = new boolean[yLast+1][xLast+1];
		Node1844[][] pre = new Node1844[yLast+1][xLast+1];
		
		int distance = 1;
		
		Queue<Node1844> q = new LinkedList<>();
		q.offer(start);
		boolean didFound = false;
		while(!q.isEmpty() && !visited[yLast][xLast]) {
			Node1844 node = q.poll();
			int y = node.y;
			int x = node.x;
			visited[y][x] = true;
			
			if (y == yLast && x == xLast) {
				didFound = true;
				break;
			}
			
			// 상 y-1
			if (y-1 >= 0 && maps[y-1][x] == 1 && !visited[y-1][x]) {
				pre[y-1][x] = node;
				q.offer(new Node1844(y-1, x));
			}
			
			// 하 y+1
			if (y+1 <= yLast && maps[y+1][x] == 1 && !visited[y+1][x]) {
				pre[y+1][x] = node;
				q.offer(new Node1844(y+1, x));
			}
			
			// 좌 x-1
			if (x-1 >= 0 && maps[y][x-1] == 1 && !visited[y][x-1]) {
				pre[y][x-1] = node;
				q.offer(new Node1844(y, x-1));
			}
			
			// 우 x+1
			if (x+1 <= xLast && maps[y][x+1] == 1 && !visited[y][x+1]) {
				pre[y][x+1] = node;
				q.offer(new Node1844(y, x+1));
			}
		}
		
		if (didFound) {
			Node1844 preNode = pre[yLast][xLast];
			while (preNode != null) {
				preNode = pre[preNode.y][preNode.x];
				distance++;
			}
		} else {
			distance = -1;
		}
		
		return distance;
	}
}

class Node1844 {
	int y;
	int x;
	
	public Node1844(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class ROR1844Slow {
	public static void main(String[] args) {
		Solution1844 solution = new Solution1844();
		int ans1 = solution.solution(new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}});
		System.out.println(ans1);
	}
}
