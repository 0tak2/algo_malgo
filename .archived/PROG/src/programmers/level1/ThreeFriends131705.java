package programmers.level1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution131705 {
    public int solution(int[] number) {
        int answer = 0;

        Set<String> set = new HashSet<>();

        for (int i=0; i<number.length; i++) {
            for (int j=0; j<number.length; j++) {
                Inner: for (int k=0; k<number.length; k++) {
                    // if (i == 0 && j == 1 && k == 4) System.out.println("!!!!");
                    if (number[i] + number[j] + number[k] == 0) {
                        // if (number[i] == 0 && number[j] == 0 && number[k] == 0) {
                        // System.out.println("asdf");
                        // System.out.println("asdf: " + i + " " + j + " " + k);
                        // }


                        if (i == j || j == k ||  i == k) {
                            continue Inner;
                        }

//                      System.out.println(i + " " + j + " " + k);
//                      System.out.println(number[i] + " " + number[j] + " " + number[k]);
//                      System.out.println();
                        int[] tmp = new int[3];
                        tmp[0] = i;
                        tmp[1] = j;
                        tmp[2] = k;

                        Arrays.sort(tmp);

                        StringBuilder sb = new StringBuilder();

                        for (int n : tmp) {
                            sb.append(Integer.toString(n));
                        }

                        set.add(sb.toString());
                    }
                }
            }
        }

        // System.out.println("FOUND: " + set);

        return set.size();
    }
}

public class ThreeFriends131705 {

}
