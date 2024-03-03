package kr.pe.otag2.study.icote.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Team_10_7 {
    private static int find(int[] parentTable, int target) {
        if (parentTable[target] != target) {
            parentTable[target] = find(parentTable, parentTable[target]);
        }
        return parentTable[target];
    }

    private static boolean areSameTeam(int[] parentTable, int target1, int target2) {
        return parentTable[target1] == parentTable[target2];
    }

    private static void union(int[] parentTable, int target1, int target2) {
        int parent1 = find(parentTable, target1);
        int parent2 = find(parentTable, target2);

        if (parent1 < parent2) {
            parentTable[parent2] = parent1;
            return;
        }
        parentTable[parent1] = parent2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int lastNumber = Integer.parseInt(inputs[0]);
        int totalOps = Integer.parseInt(inputs[1]);

        int[] parentTable = new int[lastNumber + 1];
        for (int i=0; i<=lastNumber; i++) {
            parentTable[i] = i;
        }

        for (int i=0; i<totalOps; i++) {
            inputs = br.readLine().split(" ");
            int opCode = Integer.parseInt(inputs[0]);
            int arg1 = Integer.parseInt(inputs[1]);
            int arg2 = Integer.parseInt(inputs[2]);

            if (opCode == 0) {
                // union
                union(parentTable, arg1, arg2);
                continue;
            }

            if (opCode == 1) {
                // same team
                boolean result = areSameTeam(parentTable, arg1, arg2);
                if (result) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
                continue;
            }
        }
    }
}
