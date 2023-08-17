package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS이지만 최단거리가 아닌 넓이를 구해야한다는 점에 헷갈려서 오래걸렸다.
 */
public class BOJ1926 {
    static int[][] map;
    static boolean[][] visited;
    static int[][] distance;

    final static int[] directionY = {-1, +1, 0, 0};
    final static int[] directionX = {0, 0, -1, +1};

    public static int bfs(int startY, int startX) {
        if (visited[startY][startX]) return 0; // 모든 1에 대해 돌 것이기 때문에 방문 여부 체크

        Queue<int[]> q = new LinkedList<>();

        visited[startY][startX] = true;
        q.offer(new int[] {startY, startX});

        int pollCount = 0;
        while(!q.isEmpty()) {
            int[] node = q.poll();
            pollCount++; // 최단 거리와 다르게 넓이는 큐에서 뽑은 횟수를 세면 된다
            int y = node[0];
            int x = node[1];

            for (int l=0; l<4; l++) {
                int newY = y + directionY[l];
                int newX = x + directionX[l];

                if (newY < 0 || newX < 0 || newY > map.length-1 || newX > map[0].length-1) {
                    continue;
                }

                if (visited[newY][newX]) {
                    continue;
                }

                if (map[newY][newX] == 0) {
                    continue;
                }

                q.offer(new int[] {newY, newX});
                visited[newY][newX] = true;
            }
        }
        
        return pollCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        List<Integer> areaRank = new ArrayList<>();

        int dimensionY = Integer.parseInt(input[0]);
        int dimensionX = Integer.parseInt(input[1]);

        map = new int[dimensionY][dimensionX];
        visited = new boolean[dimensionY][dimensionX];
        distance = new int[dimensionY][dimensionX];

        for (int l=0; l<dimensionY; l++) {
            String[] valueArr = br.readLine().split(" ");
            for (int m=0; m<dimensionX; m++) {
                map[l][m] = Integer.parseInt(valueArr[m]);
            }
        }

        // 모든 1에 대해 bfs
        for (int l=0; l<dimensionY; l++) {
            for (int m=0; m<dimensionX; m++) {
                if (map[l][m] == 1) {
                    int result = bfs(l, m);
//                    System.out.println("l, m, result: " + l + ", " + m + ", " + result);
                    if (result > 0) {
                        areaRank.add(result); // 넓이가 1 이상인 경우에만 탐색한 것으로 침
                    }
                }
            }
        }

        if (areaRank.isEmpty()) {
            System.out.println(0 + "\n" + 0);
        } else {
            Collections.sort(areaRank);
            System.out.println(areaRank.size() + "\n" + areaRank.get(areaRank.size()-1));
        }
//        System.out.println(areaRank);
        br.close();
    }
}
