package kr.pre.otag2.study.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Tomato_7576 {
    private static final int[] dx = new int[]{-1, 1, 0, 0};
    private static final int[] dy = new int[]{0, 0, -1, 1};
    private static int[][] map;
    private static int[][] distance;
    private static List<int[]> startPosList = new ArrayList<>();
    private static int numOfGreenTomato = 0;

    private static void checkInputs() {
        int w = map[0].length;
        int h = map.length;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("Start Point: ");
        for (int[] pos : startPosList) {
            System.out.print(pos[0] + ", " + pos[1] + " / ");
        }
        System.out.println();

        System.out.println("익은 토마토 수: " + startPosList.size());
        System.out.println("안 익은 토마토 수: " + numOfGreenTomato);
    }

    private static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();

        // 첫 지점 방문 처리
        for (int[] pos : startPosList) {
            q.offer(pos);
        }

        while (!q.isEmpty()) {
            int[] target = q.poll();
            int y = target[0];
            int x = target[1];
            int prevDistance = distance[y][x];

            // System.out.println("방문: " + y + ", " + x + " / 누적: " + prevDistance + " / 남은 안 익은 토마토 수: " + numOfGreenTomato);

            if (prevDistance > 0) { // 첫 방문인 경우를 제외
                numOfGreenTomato--;
            }

            // 토마토가 모두 익으면 종료
            if (numOfGreenTomato == 0) {
                // System.out.println("토마토 모두 익음: " + y + ", " + x);
                return prevDistance;
            }

            for (int i = 0; i < 4; i++) {
                int newY = y + dy[i];
                int newX = x + dx[i];

                // 맵 안에 있는지 검사
                if (newY < 0 || newY > map.length - 1 || newX < 0 || newX > map[0].length - 1) {
                    continue;
                }

                // 빈 칸인 경우
                if (map[newY][newX] == -1) {
                    continue;
                }

                // 방문 이력 검사
                if (distance[newY][newX] > 0) {
                    continue;
                }

                // 이미 익은 경우
                if (map[newY][newX] == 1) {
                    continue;
                }

                // 방문 처리
                map[newY][newX] = 1;
                distance[newY][newX] = prevDistance + 1;
                q.offer(new int[] {newY, newX});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputRaw = br.readLine().split(" ");
        int w = Integer.parseInt(inputRaw[0]);
        int h = Integer.parseInt(inputRaw[1]);

        map = new int[h][w];
        distance = new int[h][w];

        for (int i = 0; i < h; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(row[j]);
                if (map[i][j] == 1) {
                    startPosList.add(new int[]{i, j});
                    continue;
                }

                if (map[i][j] == 0) {
                    numOfGreenTomato++;
                }
            }
        }

        // checkInputs();

        // 익은 토마토가 하나도 없는 경우
        if (startPosList.isEmpty()) {
            System.out.println(-1);
            return;
        }

        // 안익은 토마토가 하나도 없는 경우
        if (numOfGreenTomato == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs());
    }
}
