package kr.pe.otag2.study.icote.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SearchProduct_7_5 {
    private int binarySearch(int[] source, int target) {
        int start = 0;
        int end = source.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int midVal = source[mid];

            if (midVal == target) {
                return mid;
            } else if (midVal > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    // 이진 정렬
    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int productTotal = Integer.parseInt(br.readLine());
        int[] productList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(productList);

        int orderTotal = Integer.parseInt(br.readLine());
        int[] requestList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int req : requestList) {
            int reqIndex = binarySearch(productList, req);
            if (reqIndex == -1) {
                System.out.print("no ");
            } else {
                System.out.print("yes ");
            }
        }
    }

    // 계수 정렬
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int productTotal = Integer.parseInt(br.readLine());
        String[] productList = br.readLine().split(" ");
        boolean[] productRecord = new boolean[1_000_001];
        for (String productCode : productList) {
            productRecord[Integer.parseInt(productCode)] = true;
        }

        int orderTotal = Integer.parseInt(br.readLine());
        int[] requestList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int req : requestList) {
            if (productRecord[req]) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        }
    }

    // 집합
    public void solution3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int productTotal = Integer.parseInt(br.readLine());
        String[] productList = br.readLine().split(" ");
        Set<Integer> productSet = new HashSet<>();
        for (String productCode : productList) {
            productSet.add(Integer.parseInt(productCode));
        }

        int orderTotal = Integer.parseInt(br.readLine());
        int[] requestList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int req : requestList) {
            if (productSet.contains(req)) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SearchProduct_7_5 solution = new SearchProduct_7_5();
        solution.solution3();
    }
}
