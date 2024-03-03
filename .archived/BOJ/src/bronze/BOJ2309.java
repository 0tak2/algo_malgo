package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] nanjangee = new int[9];
        int[] breakNanjangee = new int[2];

        for (int i=0; i<9; i++) {
            nanjangee[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nanjangee); // 출력을 오름차순으로 해야하므로 미리 정렬해둔다.

        Loop:
        for (int l=0; l<9; l++) { // 제외할 두 개의 인덱스를 찾는다.
            for (int m=0; m<9; m++) {
                if (l==m) continue;

                int sum = 0;
                for (int n=0; n<9; n++) {
                    if (n==l || n==m) continue;
                    sum += nanjangee[n];
                }

                if (sum == 100) { // 합이 100이 되는 경우 제외한 인덱스를 기록한다.
                    breakNanjangee[0] = l;
                    breakNanjangee[1] = m;
                    break Loop;
                }
            }
        }

        for (int i=0; i<9; i++) { // 제외할 인덱스를 제외하고 출력한다.
            if (i == breakNanjangee[0] || i == breakNanjangee[1]) continue;

            sb.append(nanjangee[i] + "\n");
        }

        System.out.println(sb);
    }
}
