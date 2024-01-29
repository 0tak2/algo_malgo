package kr.pe.otag2.study.icote.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Binary_7_2 {
    /**
     * 이진 탐색 (재귀함수)
     * @param array 대상 배열
     * @param target 찾을 값
     * @param start 탐색 시작 위치
     * @param end 탐색 종료 위치
     * @return 찾은 원소의 인덱스. 찾지 못하면 -1을 반환한다.
     */
    public int search1(int[] array, int target, int start, int end) {
        if (start > end) {
            return -1; // target이 array에 없으면 start가 커지든 end가 작아지든 이 조건을 만족하게 된다.
        }

        int mid = (start + end) / 2;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            return search1(array, target, start, mid-1);
        } else {
            return search1(array, target, mid+1, end);
        }
    }

    /**
     * 이진 탐색 (반복문)
     * @param array 대상 배열
     * @param target 찾을 값
     * @param start 탐색 시작 위치
     * @param end 탐색 종료 위치
     * @return 찾은 원소의 인덱스. 찾지 못하면 -1을 반환한다.
     */
    public int search2(int[] array, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(inputArray);
        int target = Integer.parseInt(br.readLine());

        Binary_7_2 binarySearcher = new Binary_7_2();
        int indexByRecursion = binarySearcher.search1(inputArray, target, 0, inputArray.length-1);
        int indexByLoop = binarySearcher.search2(inputArray, target, 0, inputArray.length-1);

        assert indexByRecursion == indexByLoop;

        if (indexByRecursion != -1) {
            System.out.println("Index: " + indexByRecursion);
        } else {
            System.out.println("Not Found");
        }
    }
}
