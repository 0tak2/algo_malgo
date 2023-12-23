package kr.pe.otag2.study.icote.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LRUD_4_1 {
    private final List<String> directions = List.of("L", "R", "U", "D");
    private final int[] dx = new int[] {-1, +1, 0, 0};
    private final int[] dy = new int[] {0, 0, -1, +1};

    public void solution() throws IOException {
        int x = 1;
        int y = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dimension = Integer.parseInt(br.readLine());
        String[] cmds = br.readLine().split(" ");
        for (String cmd : cmds) {
            int direction = directions.indexOf(cmd);
            int nextX = x + dx[direction];
            int nextY = y + dy[direction];

            if (nextX <= 0 || nextX > dimension || nextY <= 0 || nextY > dimension) {
                continue;
            }
            x = nextX;
            y = nextY;
        }

        System.out.println(y + " " + x);
    }

    public static void main(String[] args) throws IOException {
        LRUD_4_1 solution = new LRUD_4_1();
        solution.solution();
    }
}
