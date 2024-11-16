package io.github.otak2.leetcode.learn.arrayandstring.ch4;

import java.util.ArrayList;
import java.util.List;

/**
 * Pascal's Triangle II
 *
 * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1171/
 *
 * 이전 행을 기억하고 다음 행을 계산하도록 구현
 *
 * 2ms, 41.4MB
 */
public class PascalsTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return List.of(1);
        }

        List<Integer> prevRow = new ArrayList<>(List.of(1, 1));
        int idx = 1;

        while (idx < rowIndex) {
            idx++;

            List<Integer> row = new ArrayList<>(prevRow);
            row.add(1);
            for (int i = 1; i < prevRow.size(); i++) {
                row.set(i, prevRow.get(i - 1) + prevRow.get(i));
            }

            if (idx == rowIndex) {
                return row;
            }
            prevRow = row;
        }

        return prevRow;
    }
}
