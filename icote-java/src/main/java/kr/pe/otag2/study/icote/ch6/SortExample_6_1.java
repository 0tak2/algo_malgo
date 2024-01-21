package kr.pe.otag2.study.icote.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SortExample_6_1 {
    /**
     * 선택 정렬
     */
    public int[] selectionSort(int[] target) {
        if (target.length < 2) {
            return target;
        }

        for (int i=0; i < target.length; i++) {
            // i는 정렬되지 않은 범위의 첫 원소의 인덱스가 된다
            int minIndex = i;

            for (int j=i+1; j < target.length; j++) {
                // 정렬되지 않은 범위에서 최소값을 조사한다
                if (target[j] < target[minIndex]) {
                    minIndex = j;
                }
            }

            int tmp = target[i];
            target[i] = target[minIndex];
            target[minIndex] = tmp;
        }

        return target;
    }

    /**
     * 삽입 정렬
     */
    public int[] insertionSort(int[] target) {
        if (target.length < 2) {
            return target;
        }

        for (int i=1; i < target.length; i++) {
            // i 이전까지의 범위는 정렬된 것으로 보며, i는 삽입할 값의 인덱스가 된다.
            // 즉, 첫 원소는 정렬된 것으로 간주하는 것으로, 시작 점은 0번이 아닌 1번이다.
            for (int j=i; j >= 1; j--) { // 왼편의 원소를 조사하여 자리를 바꾸는 방식으로 삽입하기 떄문에 1번까지만 보면 된다.
                if (target[j] < target[j-1]) { // 왼편의 원소가 더 크면 자리를 바꾼다.
                    int tmp = target[j];
                    target[j] = target[j-1];
                    target[j-1] = tmp;
                } else { // 왼편의 원소가 더 작거나 같으면 그 자리에서 멈추고, 다음 루프를 진행한다.
                    break;
                }
            }
        }

        return target;
    }

    /**
     * 퀵 소트
     */
    public void quickSort(int[] array, int startIdx, int endIdx) {
        // 재귀 함수로 사용할 것이므로 종료 조건을 명시
        if (startIdx >= endIdx) {
            return;
        }

        int pivot = startIdx; // 피벗은 범위의 가장 왼쪽
        int left = pivot + 1; // 왼쪽 커서는 피벗 한 칸 뒤에서 시작
        int right = endIdx; // 오른쪽 커서는 범위 마지막 원소에서 시작

        // 왼쪽 커서와 오른쪽 커서가 엇갈리기 전까지 반복:
        // 왼쪽에서는 피벗보다 큰 수를 찾고,
        // 오른쪽에서는 피벗보다 작은 수를 찾아서 자리를 바꾸기
        while (left <= right) {
            while (left <= endIdx && array[pivot] >= array[left]) { // 피벗보다 큰 수를 찾을 때까지 커서를 옮긴다
                // todo: 왜 left는 endIdx와 같아도 되지? 아래의 while문을 보면, right와 startIdx 비교에는 등호가 빠져있다
                left++;
            }

            while (right > startIdx && array[pivot] <= array[right]) { // 피벗보다 작은 수를 찾을 때까지 커서를 옮긴다
                right--;
            }

            // 탐색 완료

            if (left > right) { // 엇갈린 경우 피벗과 작은 수의 자리를 바꾼다 (right 커서가 작은 수를 가리키고 있음)
                int tmp = array[pivot];
                array[pivot] = array[right];
                array[right] = tmp;
                // 이 경우 while문을 빠져나가게 됨.
            } else { // 아니면 큰 수와 작은 수의 자리를 바꾼다
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }
        }

        // 새로운 피벗 자리는 right가 가리키고 있고, 그 왼쪽과 오른쪽으로 2분할됨
        quickSort(array, startIdx, right-1); // 왼쪽 범위에서 퀵소트
        quickSort(array, right+1, endIdx); // 오른쪽 범위에서 퀵소트
    }

    /**
     * 계수 정렬
     */
    public int[] countingSort(int[] target) {
        StringBuilder sb = new StringBuilder();

        int[] countingTable = new int[Arrays.stream(target).max().orElse(0) + 1];
        for (int i : target) {
            countingTable[i]++;
        }

        for (int i=0; i<countingTable.length; i++) {
            for (int j=0; j<countingTable[i]; j++) {
                sb.append(i);
                sb.append(" ");
            }
        }

        return Arrays.stream(sb.toString().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static void main(String[] args) throws IOException {
        SortExample_6_1 solution = new SortExample_6_1();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] originalArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println("선택 정렬");
        System.out.println(Arrays.toString(solution.selectionSort(originalArr)));
        System.out.println();

        System.out.println("삽입 정렬");
        System.out.println(Arrays.toString(solution.insertionSort(originalArr)));
        System.out.println();

        System.out.println("퀵 정렬");
        solution.quickSort(originalArr, 0, originalArr.length-1);
        System.out.println(Arrays.toString(originalArr));
        System.out.println();

        System.out.println("계수 정렬");
        System.out.println(Arrays.toString(solution.countingSort(originalArr)));
        System.out.println();
    }
}
