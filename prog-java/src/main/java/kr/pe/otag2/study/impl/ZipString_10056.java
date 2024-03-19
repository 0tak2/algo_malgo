package kr.pe.otag2.study.impl;

import java.util.ArrayList;
import java.util.List;

public class ZipString_10056 {
    public String zip(String payload, int option) {
        List<String> group = new ArrayList<>(payload.length());
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<payload.length(); i+=option) {
            if (i + option > payload.length() - 1) {
                group.add(payload.substring(i));
                continue;
            }

            group.add(payload.substring(i, i + option));
        }
//        System.out.println("group: " + group);

        String prev = null;
        int count = 0;
        for (String g : group) {
            if (!g.equals(prev)) {
                if (prev != null) {
                    if (count != 1) {
                        sb.append(count);
                    }
                    sb.append(prev);
                    count = 0;
                }

                prev = g;
                count++;
                continue;
            }

            count++;
        }

        if (count > 0) {
            if (count != 1) {
                sb.append(count);
            }
            sb.append(prev);
        }

//        System.out.println(sb);

        return sb.toString();
    }

    public int solution(String s) {
        int min = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            int result = zip(s, i).length();
            if (result < min) {
                min = result;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        ZipString_10056 solution = new ZipString_10056();
        int answer = solution.solution("a");
        System.out.println(answer);
    }
}
