package io.github.otak2;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    static String[] chars = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Test
    void stringHashCodeTest() {
        for (int i=0; i<26; i++) {
            System.out.println("HashCode of " + chars[i] + ": " + chars[i].hashCode());
            assertEquals(String.valueOf((char) ('A' + i)).hashCode(), chars[i].hashCode());
        }
    }

    @Test
    void benchHashMap() {
        Map<String, Integer> map = new HashMap<>();
        Integer[] arr = new Integer[26];

        // 해시맵 입출력
        Long step1Start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            for (int j=0; j<26; j++) {
                map.compute(chars[j], (key, value) -> value == null ? 0 : value + 1);
            }
        }
        Long step1End = System.currentTimeMillis();

        // 단순 배열 입출력
        Long step2Start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            for (int j=0; j<26; j++) {
                if (arr[j] == null) {
                    arr[j] = 0;
                } else {
                    arr[j]++;
                }
            }
        }
        Long step2End = System.currentTimeMillis();

        System.out.println(step1End - step1Start + "ms");
        System.out.println(step2End - step2Start + "ms");
    }
}
