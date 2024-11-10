package io.github.otak2.leetcode.grind75;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode 733. Flood Fill
 * Grind75 #9
 * https://leetcode.com/problems/flood-fill/description/
 *
 * DFS로 인접 영역을 조사해 색칠
 *
 * 1ms, 45.06MB
 */
public class FloodFill {
    // 상, 하, 좌, 우
    private final int[] dy = new int[] {-1, 1, 0, 0};
    private final int[] dx = new int[] {0, 0, -1, 1};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Deque<int[]> st = new ArrayDeque<>();
        boolean[][] visited = new boolean[image.length][image[0].length];
        int originalColor = image[sr][sc];

        st.offerFirst(new int[] {sr, sc});
        while (!st.isEmpty()) {
            int[] p = st.pollFirst();
            int y = p[0];
            int x = p[1];

            image[y][x] = color;

            for (int i=0; i<4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < image.length && nx >= 0 && nx < image[0].length
                        && image[ny][nx] == originalColor
                        && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    st.offerFirst(new int[] {ny, nx});
                }
            }
        }

        return image;
    }
}
