package org.example.math;

/**
 * Greatest Common Divisor
 * 최대공약수
 */
public class GCD {
    private int getGcd(int a, int b) {
        int result = 1;

        for (int i = 2; i <= Integer.min(a, b); i++) {
            while (a % i == 0 && b % i == 0) {
                a = a / i;
                b = b / i;
                result *= i;
            }
        }

        return result;
    }

    private int getGcdByEuclid(int a, int b) {
        int result = 1;

        // todo: 유클리드 호제법

        return result;
    }

    public static void main(String[] args) {
        GCD gcd = new GCD();
        System.out.println(gcd.getGcd(3, 5)); // 1
        System.out.println(gcd.getGcd(28, 14)); // 14
        System.out.println(gcd.getGcd(36, 58)); // 2
    }
}
