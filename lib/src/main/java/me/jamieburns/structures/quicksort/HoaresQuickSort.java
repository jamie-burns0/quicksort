package me.jamieburns.structures.quicksort;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class HoaresQuickSort {

    public void quickSort(int[] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }

        Deque<Integer> stack = new LinkedList<>();

        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.isEmpty() ) {

            int hi = stack.pop();
            int lo = stack.pop();

            int p = partition(arr, lo, hi);

            if( hi - lo <= 2 )
            {
                continue;
            }

            if( p > lo )
            {
                stack.push(lo);
                stack.push(p);
            }

            if( p + 1 < hi )
            {
                stack.push(p + 1);
                stack.push(hi);
            }
        }
    }

    private int partition(int[] arr, int lo, int hi) {

        int pivot = arr[lo];
        
        lo--;
        hi++;

        while (true) {

            do {
                lo++;
            } while (arr[lo] < pivot);

            do {
                hi--;
            } while (arr[hi] > pivot);

            if (lo >= hi) {
                return hi;
            }

            swap(arr, lo, hi);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String... args)
    {
        var arr = List.of( 10, 5, 15, 8, 7, 8, 2, 6, 4, 9, 1 ).stream().mapToInt( Integer::intValue ).toArray();

        var quickSort = new HoaresQuickSort();

        quickSort.quickSort( arr );

        System.out.println("arr=%s".formatted(Arrays.toString(arr)));
    }
}