package kr.pe.otag2.study.impl;

/**
 * fixme
 * 정확성 테스트 73.0 / 100.0
 * 실패: 1, 23 ~ 33
 */
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
        if (key.length > lock.length) {
            return false;
        }

        int expandedDimension = lock.length + (key.length - 1) * 2; // 한 줄은 겹치게 된다.
        int[][] expandedLock = new int[expandedDimension][expandedDimension];
        for (int h = 0; h < lock.length; h++) {
            for (int w = 0; w < lock.length; w++) {
                int value = lock[h][w];
                expandedLock[key.length - 1 + h][key.length - 1 + w] = value; // 그려보기
            }
        }

        boolean found = false;
        // 회전
        for (int i = 0; i < 4; i++) {
            int[][] rotatedKey = rotate90(key);
            // 시작점
            for (int h = 0; h <= expandedLock.length - key.length; h++) {
                for (int w = 0; w <= expandedLock.length - key.length; w++) {
                    SEARCH:
                    for (int j = 0; j < key.length; j++) {
                        for (int k = 0; k < key.length; k++) {
                            if (rotatedKey[j][k] == expandedLock[h + j][w + k]) {
                                continue SEARCH;
                            }
                        }
                    }
                    found = true;
                    break;
                }
            }
        }

        return found;
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
