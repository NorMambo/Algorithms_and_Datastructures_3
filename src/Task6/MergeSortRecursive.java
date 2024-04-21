package Task6;

import java.util.Arrays;

public class MergeSortRecursive {
    public MergeSortRecursive() {}

    private void merge1arr(int[] a, int low, int mid, int high) {

        int[] b = new int[high+1];
        int i, j, k;
        i = low; j = mid + 1; k = low;

        // copy one int at a time until one of the counters reaches the array limit
        while (i <= mid && j <= high) {
            if (a[i] < a[j])
                b[k++] = a[i++];
            else
                b[k++] = a[j++];
        }

        // once one of the counters has reached the limit, only one of the following will execute
        for (; i <= mid; i++)
            b[k++] = a[i];
        for (; j <= high; j++)
            b[k++] = a[j];
        for (i = low; i <= high; i++)
            a[i] = b[i];
    }
//    private int[] merge2arr(int[] a, int[] b) {
//
//        int[] merged = new int[a.length + b.length];
//        int i, j, k;
//        i = j = k = 0;
//
//        // copy one int at a time until one of the counters reaches the array limit
//        while (i < a.length && j < b.length) {
//            if (a[i] < b[j])
//                merged[k++] = a[i++];
//            else
//                merged[k++] = b[j++];
//        }
//
//        // once one of the counters has reached the limit, only one of the following will execute
//        for (; i < a.length; i++)
//            merged[k++] = a[i];
//        for (; j < b.length; j++)
//            merged[k++] = b[j];
//
//        return merged;
//    }

    public void sort(int[] arr) {
        _sort(arr,0,arr.length-1);
    }
    private void _sort(int[] arr, int low, int high) {

         if (low < high) {
            int mid = (low + high)/2;
            _sort(arr, low, mid);
            _sort(arr ,mid + 1, high);
            merge1arr(arr, low, mid, high);
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 4, 7, 9, 10, 11, 30};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 55};

        int[] test1 = {11, 12, 11, 10, 9, 8, 2, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1};

        MergeSortRecursive ms = new MergeSortRecursive();
        ms.sort(test1);
        System.out.println(Arrays.toString(test1));


    }
}
