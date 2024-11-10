package io.github.otak2.leetcode.grind75;

import java.util.ArrayDeque;
import java.util.Deque;

public class FloodFillVerbose {
    private final int[] dy = new int[] {-1, 1, 0, 0};
    private final int[] dx = new int[] {0, 0, -1, 1};

    public int[][] floodFill(int[][] image, int sr, int sc, int color, boolean useDqAsStack) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[image.length][image[0].length];
        int originalColor = image[sr][sc];

        dq.offerFirst(new int[] {sr, sc});
        while (!dq.isEmpty()) {
            int[] p = dq.pollFirst();
            int y = p[0];
            int x = p[1];

            image[y][x] = color;
            System.out.println("visited: " + y + ", " + x); // row, column

            for (int i=0; i<4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < image.length && nx >= 0 && nx < image[0].length
                        && image[ny][nx] == originalColor
                        && !visited[ny][nx]) {
                    System.out.println("offered: " + ny + ", " + nx);

                    visited[ny][nx] = true;
                    if (useDqAsStack) {
                        dq.offerFirst(new int[] {ny, nx});
                    } else {
                        dq.offerLast(new int[] {ny, nx});
                    }
                }
            }
            System.out.println("Current St/Q: " + dqToString((ArrayDeque)dq));
            System.out.println();
        }

        return image;
    }

    String dqToString(ArrayDeque<?> dq) {
        StringBuilder sb = new StringBuilder();
        Object[] ar = dq.toArray();

        for (Object o : ar) {
            int[] pos = (int[])o;
            sb.append("(");
            sb.append(pos[0]);
            sb.append(", ");
            sb.append(pos[1]);
            sb.append(") ");
        }

        return sb.toString();
    }
}
