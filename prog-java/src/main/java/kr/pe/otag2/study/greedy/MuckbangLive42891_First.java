package kr.pe.otag2.study.greedy;

import java.util.Arrays;

public class MuckbangLive42891_First {
    public int solution(int[] food_times, long k) {
        int nextTurn = 0;
        int timeCount = 0;
        boolean completed = false;

        while (timeCount < k) {
            if (food_times[nextTurn] == 0) {
                nextTurn = (nextTurn + 1) % food_times.length;
                continue;
            }

            food_times[nextTurn]--;
            nextTurn = (nextTurn + 1) % food_times.length;
            timeCount++;

            // 모두 0이면 완료 플래그 지정
            if (Arrays.stream(food_times).filter(i -> i == 0).count() == food_times.length) {
                completed = true;
                break;
            }
        }

        if (completed) {
            return -1;
        }

        // 유효한 키인지 검사
        while (food_times[nextTurn] == 0) {
            nextTurn = (nextTurn + 1) % food_times.length;
        }

        return nextTurn + 1;
    }

    public static void main(String[] args) {
        MuckbangLive42891_First solution = new MuckbangLive42891_First();
//        int result = solution.solution(new int[] {3, 1, 2}, 6);
        int result = solution.solution(new int[] {3, 1, 1, 1, 2, 4, 3}, 12);
        System.out.println(result);
    }
}
