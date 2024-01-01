package kr.pe.otag2.study.icote.ch5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 문제 정확히 다 읽고, 계획 확실히 잡은 후 코딩
 * 최단 거리 => bfs
 */
public class Maze_5_4 {
    public final int[] dy = new int[] {0, 0, -1, +1};
    public final int[] dx = new int[] {-1, +1, 0, 0};

    private int bfs(int[][] graph, int y, int x, int[][] record) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {y, x});
        record[y][x] = 1;

        while (q.peek() != null) {
            int[] v = q.poll();
            int vy = v[0];
            int vx = v[1];
            graph[vy][vx] = 0;

            for (int i=0; i<4; i++) {
                int ny = vy + dy[i];
                int nx = vx + dx[i];

                if (ny < 0 || ny > graph.length-1 || nx < 0 || nx > graph[0].length-1) {
                    continue;
                }

                if (graph[ny][nx] == 0) {
                    continue;
                }

                q.offer(new int[] {ny, nx});

                // * 거리의 축적 - 책에서 확인한 아이디어
                if (record[ny][nx] == 0) {
                    record[ny][nx] = record[vy][vx] + 1;
                }
            }
        }

        return record[record.length-1][record[0].length-1] ;
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dInputs = br.readLine().split(" ");
        int dy = Integer.parseInt(dInputs[0]);
        int dx = Integer.parseInt(dInputs[1]);

        int startY = -1;
        int startX = -1;
        int[][] graph = new int[dy][dx];
        int[][] record = new int[dy][dx];
        for (int i=0; i<dy; i++) {
            String[] rowInputs = br.readLine().split("");
            for (int j=0; j<dx; j++) {
                graph[i][j] = Integer.parseInt(rowInputs[j]);
                record[i][j] = 0;
                if (startY == -1 && startX == -1 && graph[i][j] == 1) {
                    startY = i;
                    startX = j;
                }
            }
        }

        int distance = bfs(graph, startY, startX, record);
        System.out.println(distance);
    }

    public static void main(String[] args) throws IOException {
        Maze_5_4 solution = new Maze_5_4();
        solution.solution();
    }
}
