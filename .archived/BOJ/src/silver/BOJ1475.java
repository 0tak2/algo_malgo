package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> dasomRoomNo = Arrays.stream(br.readLine().split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int[] numberTable = new int[11];

        for (int n : dasomRoomNo) {
            numberTable[n]++;
        }

        int sumOfSixNine = numberTable[6] + numberTable[9];
        numberTable[6] = sumOfSixNine / 2;
        numberTable[9] = sumOfSixNine - numberTable[6];

        Arrays.sort(numberTable);
        System.out.println(numberTable[numberTable.length-1]);
    }
}
