package Task6;

import java.util.Arrays;

public class MergeSortIterative {

    public MergeSortIterative() {}

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

    public void sort(int[] arr) {
        _sort(arr, arr.length);
    }

    private void _sort(int[] arr, int n) { // n is nr. of elements in the array
        int p, low, mid, high, i;

        for (p = 2; p <= n; p = p * 2) {

            for (i = 0; i + p - 1 < n; i = i + p) {
                low = i;
                high = i + p - 1;
                mid = (low + high)/2;

                merge1arr(arr, low, mid, high);
            }
        }
        if(p/2 < n)
            merge1arr(arr, 0, (p/2)-1, n-1);
    }

    public static void main(String[] args) {
        int[] arr1 = {8, 3, 7, 4, 9, 2, 6, 5};
        MergeSortIterative ms = new MergeSortIterative();
        ms.sort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
