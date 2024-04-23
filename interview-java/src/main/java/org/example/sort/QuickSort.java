package org.example.sort;

import java.util.Arrays;

public class QuickSort {
    private void swap(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = start;
        int left = start + 1;
        int right = end;

        while(left <= right) {
            while (left <= end && arr[left] <= arr[pivot]) {
                left++;
            }

            while (right > start && arr[right] >= arr[pivot]) {
                right--;
            }

            if (left > right) {
                swap(arr, right, pivot);
            } else {
                swap(arr, left, right);
            }
        }

        sort(arr, start, right - 1);
        sort(arr, right + 1, end);
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();

        int[] arr1 = new int[] {1, 5, 3, 8, 2, 7, 6, 4};
        sort.sort(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[] {5, 3, 2, 5, 3, 2, 1, 7, 9};
        sort.sort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));
    }
}
