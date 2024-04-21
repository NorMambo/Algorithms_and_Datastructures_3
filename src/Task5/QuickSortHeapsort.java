package Task5;

import Task4.Heapsort;

import java.util.Arrays;

public class QuickSortHeapsort {

    int[] arr;
    public QuickSortHeapsort() {}

    public int[] sort(int[] arr, int limit) {
        this.arr = arr;

        int initDepth = 0;
        _sort(this.arr, 0, arr.length - 1, initDepth, limit);

        return this.arr;
    }

    public void swap(int[]arr, int pos1, int pos2) {
        int tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;
    }

    /*
     * Sorts the elements in positions low, high and (low+high)/2.
     * The middle position will be where the pivot is located.
     * Returns the pivot position.
     */
    public int median3(int[] arr, int low, int high) {

        int middleIndex = (low + high)/2; // find middle index

        // order 1st, middle and last positions in ascending order
        if (arr[low] > arr[middleIndex])
            swap(arr, middleIndex, low);
        if (arr[low] > arr[high])
            swap(arr, high, low);
        if (arr[middleIndex] > arr[high])
            swap(arr, high, middleIndex);

        return middleIndex; // pivot will be in the middle pos, return the pivot position.
    }


    public void _sort(int[] arr, int low, int high, int currDepth, int limit) {

        if(currDepth == limit && low != high) { // when recursion depth is reached switch to heapsort
            Heapsort hs = new Heapsort(arr, low, high);
            hs.sort();
            return;
        }

        if (low < high) {

            currDepth++;

//            System.out.println("USING SORT\n");
            int pivotIndex = median3(arr, low, high);
            int pivot = arr[pivotIndex];

            swap(arr, pivotIndex, high - 1);

            int i = low;
            int j = high - 1;

            if (i == j)
                return;

            while (true) {
                while (arr[++i] < pivot) {}
                while (arr[--j] > pivot /*&& j > low*/) {}

                if (j < i)
                    break;
                else {
                    swap(arr, i, j);
                }

            }
            swap(arr, i, high - 1);

            _sort(arr, low, i - 1, currDepth, limit);
            _sort(arr,i + 1, high, currDepth, limit);
        }
    }


    public static void main(String[] args) {

        int[] arr1 = {4, 3, 2};
        int[] arr2 = {10, 4, 6, 8, 7};
        int[] arr3 = {99, 6, 3, 2, 15, -3};
        int[] arr4 = {100, 100, 7, 8, 7, 99, -4};
        int[] arr5 = {99, 100, 3, 2, 100};
        int[] arr6 = {9, 8, 5, 0, 9, 8, 5, 11, -1, 4, 9};
        int[] arr7 = {5, 6, 3, -1, 10, 4};
        int[] arr8 = {11, 10, 9, 8, 9, 7, 6, 5, 4};
        int[] arr9 = {10, 11, 6, 13, 7, 1, 9, 2, -1, 10, 11, -90, 20, -20, 18, 37, 43, 89, 100, -100, 60, -60, 65, -70};

        QuickSortHeapsort quickSort = new QuickSortHeapsort();

        int[] res = quickSort.sort(arr9, 0);
        System.out.println(Arrays.toString(arr9));
        System.out.println(Arrays.toString(res));
    }
}
