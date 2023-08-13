// 시간 초과
// 이렇게 최단거리를 구해야하는 문제는
// DFS대신 BFS가 알맞다. BFS는 각 Depth를 모두 탐색하고 다음 Depth로 넘어가기 때문에
// 가장 처음으로 도착지를 탐색하는 경우가 최단 거리임이 보장되기 때문이다.
//
// 아래에서는 dfs 함수를 재귀적으로 호출할 때마다 visited를 딥카피하여
// 가능한 모든 경로를 구하고
// 경로별 거리를 담은 배열을 정렬하여 값을 출력했다.
// (조합에 가깝다)


package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ2178Slow {
	static int[][] map;
	static List<Integer> distance;
	
	static boolean[][] copy2dArr(boolean[][] oriArr) {
		boolean[][] copied = new boolean[oriArr.length][oriArr[0].length];
		
		for (int i=0; i<copied.length; i++) {
			copied[i] = oriArr[i].clone();
		}
		
		return copied;
	}
	
	static void dfs(int y, int x, int prevDistance, boolean[][] prevVisited) {
		boolean[][] visited = copy2dArr(prevVisited);
		
		// 방문 표시
		visited[y][x] = true;
		
		// 도착 확인
		if (y == map.length-1 && x == map[0].length-1) {
			distance.add(prevDistance);
//			return;
		}
		
		// 다음으로 이동
		// 범위 넘지 않는지
		// 상
		if (y > 0 && map[y-1][x] == 1 && !visited[y-1][x]) {
			dfs(y-1, x, prevDistance+1, visited);			
		}
		
		// 하
		if (y < map.length-1 && map[y+1][x] == 1 && !visited[y+1][x]) {
			dfs(y+1, x, prevDistance+1, visited);			
		}
		
		// 좌
		if (x > 0 && map[y][x-1] == 1 && !visited[y][x-1]) {
			dfs(y, x-1, prevDistance+1, visited);			
		}
		
		// 우
		if (x < map[0].length-1 && map[y][x+1] == 1 && !visited[y][x+1]) {
			dfs(y, x+1, prevDistance+1, visited);			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] dimensionInput = br.readLine().split(" ");
		
		int dimensionY = Integer.parseInt(dimensionInput[0]);
		int dimensionX = Integer.parseInt(dimensionInput[1]);
		
		map = new int[dimensionY][dimensionX];
		boolean[][] visited = new boolean[dimensionY][dimensionX];
		distance = new ArrayList<Integer>();
		
		for (int i=0; i<dimensionY; i++) {
			String[] valStrArr = br.readLine().split("");
			for (int j=0; j<valStrArr.length; j++) {
				map[i][j] = Integer.parseInt(valStrArr[j]);
			}
		}
		
		dfs(0, 0, 1, visited); // 시작할 때 이미 1칸 이동한 것이므로 초기 거리 1
	
		Collections.sort(distance);
		System.out.println(distance.get(0));
//		System.out.println(distance);
		br.close();
	}
}
