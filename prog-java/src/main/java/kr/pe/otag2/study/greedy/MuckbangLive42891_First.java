package kr.pe.otag2.study.greedy;

import java.util.ArrayList;
import java.util.List;

// fixme: 정확성 검사 통과하도록
public class MuckbangLive42891_First {
    public int solution(int[] food_times, long k) {
        int nextTurn = 0;
        int count = 0;
        List<Integer> skipList = new ArrayList<>(200_000);

        while(skipList.size() < food_times.length && count < k) {
            if (skipList.contains(nextTurn)) {
                continue;
            }

            int nextVal = food_times[nextTurn]--;
            if (nextVal == 0) {
                skipList.add(nextTurn);
            }

            nextTurn = (nextTurn + 1) % food_times.length;
            count++;
        }

        //
        if (skipList.size() == food_times.length) {
            return -1;
        }

        while(true) {
            if (skipList.contains(nextTurn)) {
                continue;
            }

            nextTurn = (nextTurn + 1) % food_times.length;
            break;
        }


        return nextTurn + 1;
    }

    public static void main(String[] args) {
        MuckbangLive42891_First solution = new MuckbangLive42891_First();
        int result = solution.solution(new int[] {3, 1, 2}, 5);
        System.out.println(result);
    }
}
