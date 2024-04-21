package Task3;

import java.util.Arrays;

public class InsertSort {

    private int left;
    private int right;
    private int[] array;

    public InsertSort(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    public InsertSort(int[] array) {
        this.array = array;
        this.left = 0;
        this.right = array.length-1;
    }

    public void sort() {
        for (int i = left+1; i <= right; i++) {
            for (int j = i; j > left; j--) {
                if (array[j] < array[j-1]) {
                    int tmp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = tmp;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 2, 7, 6, 5};

        InsertSort is = new InsertSort(arr);
        is.sort();
        System.out.print(Arrays.toString(arr));
    }

}
