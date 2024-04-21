package org.example.math;

/**
 * Least Common Multiple
 * 최소공배수
 */
public class LCM {
    private int getLcm(int a, int b) {
        int result = 1;

        for (int i = 2; i <= Integer.min(a, b); i++) {
            while (a % i == 0 && b % i == 0) {
                a = a / i;
                b = b / i;
                result *= i;
            }
        }

        return result * a * b;
    }

    private int getLcmByEuclid(int a, int b) {
        int result = 1;

        // todo: 유클리드 호제법

        return result;
    }

    public static void main(String[] args) {
        LCM lcm = new LCM();
        System.out.println(lcm.getLcm(3, 5)); // 15
        System.out.println(lcm.getLcm(28, 14)); // 28
        System.out.println(lcm.getLcm(36, 58)); // 1044
    }
}
