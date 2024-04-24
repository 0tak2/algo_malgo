package org.example.sort;

import java.util.Arrays;

public class SelectionSort {
    private void swap (int[] arr, int idxA, int idxB) {
        int tmp = arr[idxA];
        arr[idxA] = arr[idxB];
        arr[idxB] = tmp;
    }

    private void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { // [처음] O O O O [마지막] O
            int minValIdx = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minValIdx]) {
                    minValIdx = j;
                }
            }

            if (minValIdx != i) {
                swap(arr, minValIdx, i);
            }
        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();

        int[] arr1 = new int[] {1, 5, 3, 8, 2, 7, 6, 4};
        selectionSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[] {5, 3, 2, 5, 3, 2, 1, 7, 9};
        selectionSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
