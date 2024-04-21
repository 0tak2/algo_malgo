package org.example.sort;

import java.util.Arrays;

public class BubbleSort {
    private void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    private void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i  - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();

        int[] arr1 = new int[] {1, 5, 3, 8, 2, 7, 6, 4};
        bubbleSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[] {5, 3, 2, 5, 3, 2, 1, 7, 9};
        bubbleSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
