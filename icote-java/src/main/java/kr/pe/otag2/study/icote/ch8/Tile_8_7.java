package kr.pe.otag2.study.icote.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile_8_7 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int[] table = new int[x + 1];

        table[1] = 1;
        table[2] = 3;

       for (int i=3; i <= x; i++) {
           /*
           1. i-1번까지 꽉 찬 경우 -> 2 * 1로 한 줄 채우는 경우 => 한 가지 경우
             ㅁ ㅁ ㅁ []
             ㅁ ㅁ ㅁ []

           2. i-2번까지 꽉 찬 경우 -> 2 * 2 한 개로 채우거나 1 * 2 두 개로 채우거나 => 두 가지 경우
             ㅁ ㅁ [] []
             ㅁ ㅁ [] []
            */
           table[i] = (table[i-1] + table[i-2] * 2) % 796796;
           // 매번 나머지를 구하든 최종 결과에 나머지를 구하든 같다. (나머지 연산의 분배법칙)
           // see: https://github.com/ndb796/python-for-coding-test/issues/153
       }

        System.out.println(table[x]);
    }

    public static void main(String[] args) throws IOException {
        Tile_8_7 solution = new Tile_8_7();
        solution.solution();
    }
}
