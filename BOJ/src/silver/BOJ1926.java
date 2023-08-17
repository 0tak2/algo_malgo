package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1926 {
    static int[][] map;
    static boolean[][] visited;
    static int[][] distance;

    final static int[] directionY = {-1, +1, 0, 0};
    final static int[] directionX = {0, 0, -1, +1};

    public static int bfs(int startY, int startX) {
        Queue<Integer> q = new LinkedList<>();
        
        // 여기부터 계속
        
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        List<Integer> distanceRank = new ArrayList<>();

        int dimensionY = Integer.parseInt(input[0]);
        int dimensionX = Integer.parseInt(input[1]);

        map = new int[dimensionY][dimensionX];
        visited = new boolean[dimensionY][dimensionX];
        distance = new int[dimensionY][dimensionX];

        // 모든 1에 대해 bfs
        for (int l=0; l<dimensionY; l++) {
            for (int m=0; m<dimensionX; m++) {
                if (map[l][m] == 1) {
                    int result = bfs(l, m);

                    if (result > 0) {
                        distanceRank.add(result); // 거리가 1 이상인 경우에만 탐색한 것으로 침
                    }
                }
            }
        }

        if (distanceRank.size() == 0) {
            System.out.println(0 + "\n" + 0);
        } else {
            Collections.sort(distanceRank);
            System.out.println(distanceRank.size() + "\n" + distanceRank.get(distanceRank.size()-1));
        }

        br.close();
    }
}
