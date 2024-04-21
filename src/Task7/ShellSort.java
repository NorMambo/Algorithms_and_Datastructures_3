package Task7;

import java.util.Arrays;

public class ShellSort {

    public ShellSort(){}

    public void sortWithShell(int[] arr) {

        int j;

        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                for (j = i; j >= gap && tmp < arr[j - gap]; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = tmp;
            }
        }
    }

    public void sortWithCiura(int[] arr) {

        int j;
        int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};

        for (int gap : gaps) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                for (j = i; j >= gap && tmp < arr[j - gap] ; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = tmp;
            }
        }
    }

    public void sortWithKnuth(int[] arr) {

//        int j;
        int gap = 1;
        int limit = arr.length/3;

        while (gap < limit)
            gap = gap*3 + 1;

        while (gap >= 1) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap && arr[j] < arr[j - gap]; j -= gap) {
                    swap(arr, j, j - gap);
                }
            }
            gap /=3;
        }
    }

    public void swap(int[] arr, int pos1, int pos2) {
        int tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;
    }



    public static void main(String[] args) {
        int[] arr1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr2 = {9, 5, 2, 3, 1, 1, 9, 0, 22, 7, 6, 18};
        int[] arr3 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr4 = {9, 5, 2, 3, 1, 1, 9, 0, 22, 7, 6, 18};
        int[] arr5 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr6 = {9, 5, 2, 3, 1, 1, 9, 0, 22, 7, 6, 18};

        ShellSort ss = new ShellSort();
        System.out.println();

        System.out.println("SHELL: ");
        System.out.println("UNSORTED:\n" + Arrays.toString(arr1));
        ss.sortWithShell(arr1);
        System.out.println("\nSORTED:\n" + Arrays.toString(arr1));
        System.out.println("\nUNSORTED:\n" + Arrays.toString(arr2));
        ss.sortWithShell(arr2);
        System.out.println("\nSORTED:\n" + Arrays.toString(arr2));

        System.out.println("\n-------------------------------------------\n");

        System.out.println("CIURA: ");
        System.out.println("UNSORTED:\n" + Arrays.toString(arr3));
        ss.sortWithCiura(arr3);
        System.out.println("\nSORTED:\n" + Arrays.toString(arr3));
        System.out.println("\nUNSORTED:\n" + Arrays.toString(arr4));
        ss.sortWithCiura(arr4);
        System.out.println("\nSORTED:\n" + Arrays.toString(arr4));

        System.out.println("\n-------------------------------------------\n");

        System.out.println("KNUTH: ");
        System.out.println("UNSORTED:\n" + Arrays.toString(arr5));
        ss.sortWithKnuth(arr5);
        System.out.println("\nSORTED:\n" + Arrays.toString(arr5));
        System.out.println("\nUNSORTED:\n" + Arrays.toString(arr6));
        ss.sortWithKnuth(arr6);
        System.out.println("\nSORTED:\n" + Arrays.toString(arr6));


    }
}
