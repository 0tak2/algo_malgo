package kr.pe.otag2.study.impl;

/**
 * fixme
 */
public class ConstructHouse_60061 {
    private int[] getBoEndPos(int y, int x) {
        return new int[] {y, x + 1};
    }

    private int[] getColumnEndPos(int y, int x) {
        return new int[] {y + 1, x};
    }

    private int[] getBoStartPos(int y, int x) {
        return new int[] {y, x - 1};
    }

    private int[] getColumnStartPos(int y, int x) {
        return new int[] {y - 1, x};
    }

    private boolean verifyInsideMap(int dimension, int y, int x) {
        if (y < 0 || x < 0 || y >= dimension - 1 || x >= dimension - 1) {
            return false;
        }
        return true;
    }

    private void installBo(int[][] map, int y, int x) {
        int[] endPos = getBoEndPos(y, x);
        int eY = endPos[0];
        int eX = endPos[1];

        if (!verifyInsideMap(map.length, eY, eX)) {
            return;
        }

        if (map[y][x] >= 2 || map[getColumnStartPos(y, x)[0]][getColumnStartPos(y, x)[1]] >= 2 || map[eY][eX] >= 2 || map[getColumnStartPos(eY, eX)[0]][getColumnStartPos(eY, eX)[1]] >= 2) { // 시작이나 끝점에 기둥이 있는 경우
            map[y][x] = map[y][x] == 0 ? 1 : 3;
        } else if ((map[y][x] == 1 || map[y][x] == 3 || map[getBoStartPos(y, x)[0]][getBoStartPos(y, x)[1]] == 1 || map[getBoStartPos(y, x)[0]][getBoStartPos(y, x)[1]] == 3) && (map[eY][eX] == 1 || map[eY][eX] == 3 || map[getBoStartPos(eY, x)[0]][getBoStartPos(eY, x)[1]] == 1 || map[getBoStartPos(eY, x)[0]][getBoStartPos(eY, x)[1]] == 3)) {
            // 양 점이 보와 연결된 경우
            map[y][x] = map[y][x] == 0 ? 1 : 3;
        }
    }

    private void installColumn(int[][] map, int y, int x) {
        int[] endPos = getColumnEndPos(y, x);
        int eY = endPos[0];
        int eX = endPos[1];

        if (!verifyInsideMap(map.length, eY, eX)) {
            return;
        }

        if (y == 0) {
            map[y][x] = map[y][x] == 0 ? 2 : 3;
        } else if (map[getBoStartPos(y, x)[0]][getBoStartPos(y, x)[1]] != 0 || map[getColumnStartPos(y, x)[0]][getColumnStartPos(y, x)[1]] != 0) { // 좌표가 기둥이나 보의 끝 점인 경우
            map[y][x] = map[y][x] == 0 ? 2 : 3;
        }
    }

    private void removeBo(int[][] map, int y, int x) {
        // 끝 점에 보나 기둥이 있으면 안 된다
        // 그러나 다른 보와 기둥의 시작점인 경우 상관 없다


    }

    private void removeColumn(int[][] map, int y, int x) {

    }

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        int[][] map = new int[n+1][n+1];
        // map에는 구조물의 시작점을 기록한다.
        // int[y][x]:
        // 0: 비어있음
        // 1: 보만 있음
        // 2: 기둥만 있음
        // 3: 둘 다 있음

        for (int i = 0; i < build_frame.length; i++) {
            int y = build_frame[i][1];
            int x = build_frame[i][0];
            char op = build_frame[i][2] == 0 ? 'r' : 'i';
            char type = build_frame[i][3] == 0 ? 'c' : 'b';

            if (op == 'r') {
                // 삭제
                if (type == 'c') {
                    removeColumn(map, y, x);
                } else {
                    removeBo(map, y, x);
                }
            } else {
                // 설치
                if (type == 'c') {
                    installColumn(map, y, x);
                } else {
                    installBo(map, y, x);
                }
            }
        }

        return answer;
    }
}
