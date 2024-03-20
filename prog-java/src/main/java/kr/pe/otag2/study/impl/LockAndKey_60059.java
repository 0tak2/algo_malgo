package kr.pe.otag2.study.impl;

public class LockAndKey_60059 {
    public int[][] rotate90(int[][] original) {
        int d = original.length;
        int[][] result = new int[d][d];

        for (int y = 0; y < d; y++) {
            for (int x = 0; x < d; x++) {
                result[x][d-1-y] = original[y][x];
            }
        }

        return result;
    }

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        return answer;
    }

    public void toString2dArr(int[][] arr) {
        int d = arr.length;
        for (int y = 0; y < d; y++) {
            for (int x = 0; x < d; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LockAndKey_60059 solution = new LockAndKey_60059();

        // rotate test
//        int[][] result = solution.rotate90(new int[][]{
//                {0, 0, 0},
//                {1, 0, 0},
//                {0, 1, 1}
//        });
//        solution.toString2dArr(result);

        boolean answer = solution.solution(new int[][]{
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        }, new int[][]{
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1},
        });

        System.out.println(answer);
    }
}
