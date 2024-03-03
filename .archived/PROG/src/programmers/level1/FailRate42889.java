/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42889
 * 실패율
 * 객체로 이루어진 리스트를 정렬하는 방법
 */
package programmers.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Stage implements Comparable<Stage> {
    public int id;
    public double rate;
    Stage(int id, double rate) {
        this.id = id;
        this.rate = rate;
    }

    @Override
    public int compareTo(Stage stage) {
        if (stage.rate < rate) {
            return -1;
        } else if (stage.rate > rate) {
            return 1;
        }
        return 0;
    }
}

class Solution42889 {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int allUser = stages.length;
        
        Map<Integer, Integer> nowSolving = new HashMap<>();
        List<Stage> rateList = new ArrayList<>();
        
        
        for (int i=0; i<stages.length; i++) {
            int stage = stages[i];
            nowSolving.put(stage, nowSolving.getOrDefault(stage, 0) + 1);
        }
        
        int prevSolver = 0;
        
        for (int i=1; i<=N; i++) {
            prevSolver += nowSolving.getOrDefault(i-1, 0);
            int challenger = allUser - prevSolver;
            
            int currentSolver = nowSolving.getOrDefault(i, 0);
            
            double rate = (double)currentSolver / challenger;
            
            rateList.add(new Stage(i, rate));
        }
        
        Collections.sort(rateList);
        
        for (int i=0; i<rateList.size(); i++) {
            Stage s = rateList.get(i);
            answer[i] = s.id;
        }
        
        return answer;
    }
}

public class FailRate42889 {

}
