package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int numOfCat = Integer.parseInt(input[0]);
        int goal = Integer.parseInt(input[1]);

        int[] coinValueArr = new int[numOfCat];
        int startIdx = coinValueArr.length-1;

        int numOfCoin = 0;

        for (int i=0; i<numOfCat; i++) {
            int coinValue = Integer.parseInt(br.readLine());

            coinValueArr[i] = coinValue;

            if (coinValue == goal) {
                startIdx = i;
            } else if (coinValue > goal && i > 0) {
                startIdx = i-1;
            }
        }
//        System.out.println(startIdx);

        int remain = goal;
        int idx = startIdx;
        while (remain > 0 && idx >= 0) {
            if (remain - coinValueArr[idx] < 0) {
                idx--;
            } else {
                remain -= coinValueArr[idx];
                numOfCoin++;
            }
        }

        System.out.println(numOfCoin);
    }
}
