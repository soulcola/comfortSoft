package com.petrtitov.comfortSoft.util;

import java.util.List;

public class QuickSelect {


    public static int findNthMaxInList(int n, List<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0 but was: " + n);
        }
        if (n > list.size()) {
            throw new IllegalArgumentException("n (" + n + ") can not be greater than list size: (" + list.size() + ")");
        }
        int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
        int k = nums.length - n;
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }


    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
