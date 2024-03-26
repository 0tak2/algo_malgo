package kr.pre.otag2.study.acmicpc.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake_3190 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dimension = Integer.parseInt(br.readLine());
        int numOfApple = Integer.parseInt(br.readLine());

        boolean[][] appleMap = new boolean[dimension][dimension];
        Snake snake = new Snake();

        for (int i = 0; i < numOfApple; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            appleMap[inputs[0] - 1][inputs[1] - 1] = true;
        }

        int numOfOp = Integer.parseInt(br.readLine());
        char[] ops = new char[10001];

        for (int i = 0; i < numOfOp; i++) {
            String[] inputs = br.readLine().split(" ");
            ops[Integer.parseInt(inputs[0])] = inputs[1].toCharArray()[0];
        }

        int count = 0;
        while (true) {
            // 머리 이동
            boolean success = snake.moveHead();
            int[] currentHeadPos = snake.getHead();

            // 자신의 몸과 부딪히면 게임 종료
            if (!success) {
                count++;
                break;
            }

            // 벽과 부딪히면 게임 종료
            if (currentHeadPos[0] < 0 || currentHeadPos[0] > dimension - 1) {
                count++;
                break;
            }

            if (currentHeadPos[1] < 0 || currentHeadPos[1] > dimension - 1) {
                count++;
                break;
            }

            if (appleMap[currentHeadPos[0]][currentHeadPos[1]]) {
                // 이동한 칸에 사과가 있다면 그 칸의 사과는 사라지고 꼬리는 움직이지 않음
                appleMap[currentHeadPos[0]][currentHeadPos[1]] = false;
            } else {
                // 이동한 칸에 사과가 없다면 꼬리를 당김. 즉 몸 길이 그대로
                snake.shrinkTail();
            }

            // 현재 초가 끝나면 방향 전환
            snake.moveDirection(ops[++count]);
        }

        System.out.println(count);
    }

    private static class Snake {
        List<int[]> body;
        char direction;

        public Snake() {
            body = new ArrayList<>();
            body.add(new int[]{0, 0});
            direction = 'R';
        }

        public int[] getHead() {
            return body.get(0);
        }

        public boolean moveHead() {
            int[] head = body.get(0);

            switch (direction) {
                case 'L':
                    head = new int[] {head[0], head[1] - 1};
                    break;
                case 'R':
                    head = new int[] {head[0], head[1] + 1};
                    break;
                case 'U':
                    head = new int[] {head[0] - 1, head[1]};
                    break;
                case 'D':
                    head = new int[] {head[0] + 1, head[1]};
                    break;
            }

            // 내 몸과 부딪히면 false 반환
            for (int[] pos : body) {
                if (pos[0] == head[0] && pos[1] == head[1]) {
                    return false;
                }
            }

            body.add(0, head);
            return true;
        }

        public void shrinkTail() {
            body.remove(body.size() - 1);
        }

        public void moveDirection(char op) {
            switch (direction) {
                case 'L':
                    if (op == 'L') {
                        direction = 'D';
                    } else if (op == 'D') {
                        direction = 'U';
                    }
                    break;
                case 'R':
                    if (op == 'L') {
                        direction = 'U';
                    } else if (op == 'D') {
                        direction = 'D';
                    }
                    break;
                case 'U':
                    if (op == 'L') {
                        direction = 'L';
                    } else if (op == 'D') {
                        direction = 'R';
                    }
                    break;
                case 'D':
                    if (op == 'L') {
                        direction = 'R';
                    } else if (op == 'D') {
                        direction = 'L';
                    }
                    break;
            }
        }
    }
}
