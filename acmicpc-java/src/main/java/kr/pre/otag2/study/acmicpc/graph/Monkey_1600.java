package main.java.kr.pre.otag2.study.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Monkey_1600 {
    private static int[][] map;
    private static int[][][] visited;

    private static final int[] horseDirectionY = new int[]{-1, -2, -2, -1, 1, 2, 2, +1};
    private static final int[] horseDirectionX = new int[]{-2, -1, 1, 2, -2, -1, 1, 2};
    private static final int[] monkeyDirectionY = new int[]{-1, 1, 0, 0}; // 상, 하, 좌, 우
    private static final int[] monkeyDirectionX = new int[]{0, 0, -1, 1}; // 상, 하, 좌, 우

    private static int bfs(int startY, int startX, int countLikeHorse) {
        Queue<int[]> q = new LinkedList<>(); // y, x, count
        q.add(new int[]{startY, startX, 0});
        visited[startY][startX][0] = 1;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int currentY = node[0];
            int currentX = node[1];
            int ap = node[2];
            int count = visited[currentY][currentX][ap];

            if (currentY == map.length - 1 && currentX == map[0].length - 1) {
                return count - 1;
            }

            if (ap < countLikeHorse) { // 같으면 안됨. 최소한 기회가 1번 남아있는 경우
                for(int i = 0; i < 8; i++) {
                    int y = node[0] + horseDirectionY[i];
                    int x = node[1] + horseDirectionX[i];

                    if (y < 0 || y >= visited.length || x < 0 || x >= visited[0].length) {
                        continue;
                    }

                    if(ap + 1 > visited[0][0].length - 1) {
                        continue;
                    }

                    if(map[y][x] == 1) {
                        continue;
                    }

                    if(visited[y][x][ap + 1] > 0) {
                        continue;
                    }

                    visited[y][x][ap + 1] = count + 1;
                    q.add(new int[]{y, x, ap + 1});
                }
            }

            for(int i = 0; i < 4; i++) {
                int y = node[0] + monkeyDirectionY[i];
                int x = node[1] + monkeyDirectionX[i];

                if (y < 0 || y >= visited.length || x < 0 || x >= visited[0].length) {
                    continue;
                }

                if(ap + 1 > visited[0][0].length - 1) {
                    continue;
                }

                if(map[y][x] == 1) {
                    continue;
                }

                if(visited[y][x][ap] > 0) {
                    continue;
                }

                visited[y][x][ap] = count + 1;
                q.add(new int[]{y, x, ap});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int countLikeHorse = Integer.parseInt(br.readLine());
        String[] dimensionInput = br.readLine().split(" ");
        int w = Integer.parseInt(dimensionInput[0]);
        int h = Integer.parseInt(dimensionInput[1]);

        map = new int[h][w];
        visited = new int[h][w][200];

        for (int y = 0; y < h; y++) {
            String[] inputs = br.readLine().split(" ");
            for (int x = 0; x < w; x++) {
                map[y][x] = Integer.parseInt(inputs[x]);
            }
        }

        System.out.println(bfs(0, 0, countLikeHorse));
    }
}
