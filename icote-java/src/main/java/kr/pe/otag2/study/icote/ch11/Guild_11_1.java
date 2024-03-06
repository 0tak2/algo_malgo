package kr.pe.otag2.study.icote.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Guild_11_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        List<Integer> adv = new ArrayList<>(Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).toList());
        adv.sort(Comparator.naturalOrder());

        int totalMemberOfCurrentGroup = 0;
        int totalClosedGroup = 0; // 인원이 꽉 찬 (결성이 완료된) 그룹 수

        for (int i : adv) {
            totalMemberOfCurrentGroup++; // 현재 모험가를 그룹에 넣는다

            if (totalMemberOfCurrentGroup >= i) {
                // 현재 모험가를 넣었을 때 인원 수가 현재 모험가의 공포도 이상이면, 다음 모험가부터는 새로운 그룹에 배정한다.
                totalClosedGroup++;
                totalMemberOfCurrentGroup = 0;
            }
            // 인원 수가 현재 모험가의 공포도에 미치지 못하면, 아직 출발할 수 없고 다른 모험가의 배정을 기다려야 한다.
        }

        System.out.println(totalClosedGroup);
    }
}
