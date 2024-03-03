package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 기초적인 BFS 문제
 *
 * 상하좌우 탐색 할 때,
 * 	static int[] dr = {-1, +1, 0, 0};
 * 	static int[] dc = {0, 0, -1, +1};
 * 	위와 같은 배열을 반들어서 2중 for 루프로
 *
 * 			int nr , nc;
 * 			for (int d = 0; d < 4; d++) {
 * 				nr = r + dr[d];
 * 				nc = c + dc[d];
 * 				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
 * 				if (map[nr][nc] == 0 || map[nr][nc] > 1) continue;
 * 				map[nr][nc] += map[r][c] ; // 1 + 이전값 저장
 * 				q.offer(new int[] {nr, nc});
*          }
 *  이렇게 탐색하는 방법 고민
 */
public class BOJ2178 {
	static int[][] map;
	static int[][] distance;
	static boolean[][] visited;

	static int bfs(int startY, int startX) {
		Queue<int[]> q = new LinkedList<int[]>();

		visited[startY][startX] = true;
		distance[startY][startX] = 1;
		q.offer(new int[] {startY, startX}); // q에는 방문 체크를 완료한 노드들을 추가한다.

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int y = node[0];
			int x = node[1];
			int distanceFromStart = distance[y][x];

			if (y == map.length-1 && x == map[0].length-1) {
				return distanceFromStart;
			}

			if (y > 0 && map[y-1][x] == 1 && !visited[y-1][x]) {
				visited[y-1][x] = true;
				q.offer(new int[] {y-1, x});
				distance[y-1][x] = distanceFromStart + 1; // 새로 방문한 노드까지의 거리는 이전 노드까지의 거리 + 1
			}

			// 하
			if (y < map.length-1 && map[y+1][x] == 1 && !visited[y+1][x]) {
				visited[y+1][x] = true;
				q.offer(new int[] {y+1, x});
				distance[y+1][x] = distanceFromStart + 1;
			}

			// 좌
			if (x > 0 && map[y][x-1] == 1 && !visited[y][x-1]) {
				visited[y][x-1] = true;
				q.offer(new int[] {y, x-1});
				distance[y][x-1] = distanceFromStart + 1;
			}

			// 우
			if (x < map[0].length-1 && map[y][x+1] == 1 && !visited[y][x+1]) {
				visited[y][x+1] = true;
				q.offer(new int[] {y, x+1});
				distance[y][x+1] = distanceFromStart + 1;
			}
		}

		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] dimensionInput = br.readLine().split(" ");
		
		int dimensionY = Integer.parseInt(dimensionInput[0]);
		int dimensionX = Integer.parseInt(dimensionInput[1]);
		
		map = new int[dimensionY][dimensionX];
		visited = new boolean[dimensionY][dimensionX];
		distance = new int[dimensionY][dimensionX];
		
		for (int i=0; i<dimensionY; i++) {
			String[] valStrArr = br.readLine().split("");
			for (int j=0; j<valStrArr.length; j++) {
				map[i][j] = Integer.parseInt(valStrArr[j]);
			}
		}

		System.out.println(bfs(0, 0));

		br.close();
	}
}
