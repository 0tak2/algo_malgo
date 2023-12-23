package kr.pe.otag2.study.icote.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Knight_4_3 {
    // 2 U 1 L
    // 2 U 1 R
    // 2 D 1 L
    // 2 D 1 R
    // 1 D 2 L
    // 1 D 2 R
    // 1 U 2 L
    // 1 U 2 R
    private final int[] dx = new int[] {-1, +1, -1, +1, -2, +2, -2, +2};
    private final int[] dy = new int[] {-2, -2, +2, +2, +1, +1, -1, -1};
    private final int TOTAL_MOVE = 8;

    public void solution() throws IOException {
        int x, y;
        int result = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String coordInput = br.readLine();
        x = coordInput.charAt(0) - 'a';
        y = coordInput.charAt(1) - '1';
//        System.out.println("x: " + x);
//        System.out.println("y: " + y);

        for (int i = 0; i < TOTAL_MOVE; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextX > 7 || nextY < 0 || nextY > 7) {
                continue;
            }

            result++;
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        Knight_4_3 solution = new Knight_4_3();
        solution.solution();
    }
}
