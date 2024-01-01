package kr.pe.otag2.study.icote.ch5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class IcedSoda_5_3 {
    // L, R, U, D
    private final int[] dy = new int[] {0, 0, +1, -1};
    private final int[] dx = new int[] {-1, +1, 0, 0};

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> inputs = Arrays.stream(br.readLine().split(" "))
                .map(s -> Integer.parseInt(s)).toList();

        int[][] graph = new int[inputs.get(0)][inputs.get(1)];

        for (int i=0; i<inputs.get(0); i++) {
            String[] rowInputs = br.readLine().split("");
            for (int j=0; j<inputs.get(1); j++) {
                graph[i][j] = Integer.parseInt(rowInputs[j]);
            }
        }

        boolean[][] visited = new boolean[inputs.get(0)][inputs.get(1)];
        int result = 0;
        for (int i=0; i<inputs.get(0); i++) {
            for (int j=0; j<inputs.get(1); j++) {
                if (visited[i][j]) continue;
                if (graph[i][j] == 1) continue;
                if (dfs(graph, i, j, visited, 0) > 0) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    private int dfs(int[][] graph, int vy, int vx, boolean[][] visited, int count) {
        visited[vy][vx] = true;
        count++;
//        System.out.println("Visit - y: " + vy + " x: " + vx);

        for (int i=0; i<4; i++) {
            int ny = vy + dy[i];
            int nx = vx + dx[i];

            if (ny < 0 || ny > graph.length-1 || nx < 0 || nx > graph[0].length-1) {
                continue;
            }

            if (graph[ny][nx] == 1) {
                continue;
            }

            if (visited[ny][nx]) {
                continue;
            }

            dfs(graph, ny, nx, visited, count);
        }

        return count;
    }

    /**
     * 책 풀이
     * 1. visited 배열을 따로 쓰지 않는다. 그냥 graph에 1 표시를 한다.
     * 2. 우선 dfs 메서드를 호출하여 스택에 넣는다.
     *    그 후에 dfs 메서드 초입부에서 조건에 맞는지 검사를 한다.
     * @throws IOException
     */
    private void solutionByBook() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> inputs = Arrays.stream(br.readLine().split(" "))
                .map(s -> Integer.parseInt(s)).toList();

        int[][] graph = new int[inputs.get(0)][inputs.get(1)];

        for (int i=0; i<inputs.get(0); i++) {
            String[] rowInputs = br.readLine().split("");
            for (int j=0; j<inputs.get(1); j++) {
                graph[i][j] = Integer.parseInt(rowInputs[j]);
            }
        }

        int result = 0;
        for (int i=0; i<inputs.get(0); i++) {
            for (int j=0; j<inputs.get(1); j++) {
                if (dfsByBook(graph, i, j)) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    private boolean dfsByBook(int[][] graph, int vy, int vx) {
        if (vy < 0 || vy > graph.length-1 || vx < 0 || vx > graph[0].length-1) {
            return false;
        }

        if (graph[vy][vx] == 1) {
            return false;
        }

        graph[vy][vx] = 1;

        dfsByBook(graph, vy - 1, vx);
        dfsByBook(graph, vy + 1, vx);
        dfsByBook(graph, vy, vx - 1);
        dfsByBook(graph, vy, vx + 1);

        return true;
    }

    public static void main(String[] args) throws IOException {
        IcedSoda_5_3 solution = new IcedSoda_5_3();
        solution.solutionByBook();
    }
}
