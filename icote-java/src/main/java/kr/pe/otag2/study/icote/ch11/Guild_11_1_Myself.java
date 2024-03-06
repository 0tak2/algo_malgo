package kr.pe.otag2.study.icote.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제를 반대로 이해
 * 그룹을 최대로 늘리는 방법 => 공포도 기준으로 오름차순 정렬하여 따져야함
 */
public class Guild_11_1_Myself {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        List<Integer> adv = new ArrayList<>(Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).toList());
        adv.sort(Comparator.reverseOrder());

        int result = 0;
        while (!adv.isEmpty()) {
            int minNum = adv.get(0);
            for (int i=0; i<minNum; i++) {
                if (adv.isEmpty()) {
                    break;
                }
                adv.remove(0);
            }
            result++;
        }

        System.out.println(result);
    }
}
