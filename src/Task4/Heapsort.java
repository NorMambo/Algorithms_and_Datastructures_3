package Task4;

import java.util.Arrays;

public class Heapsort {

    private int left;
    private int right;
    int[] maxBinaryHeap;
    int pos;
    int childFinder;

    // Create this to sort the entire array
    public Heapsort(int[] arr) {
        this.left = 0;
        this.right = arr.length-1;
        this.pos = 0;
        this.childFinder = 1;
        maxBinaryHeap = arr;
    }

    // Create this to partially sort the array from 'int left' to 'int right' (can also sort the entire array)
    public Heapsort(int[] arr, int left, int right) {
        this.left = left;
        this.right = right;
        this.pos = 0;
        this.childFinder = 1;
        maxBinaryHeap = arr;
    }

    private void _sink(int k) {

        /* depending on where we are starting the sorting (arr pos 0, 1, 2, 3, etc.),
         * the resulting binary tree will have a different way to find its left child.
         * We have to subtract a number from (2 * k). Turns out that pos 0 finds its
         * left child at (2 * k + 1) and right child at (2 * k + 2).
         * The procedure repeats itself for the subsequent positions 1, 2, 3, ..., n, but we
         * will have to subtract 1 from the 1st node position in the array and subtract the result
         * from 2 * k. This avoids a loop.
         */
        if (left == 0) {
            childFinder = 1;
        }
        else {
            childFinder = -(left - 1);
        }

//        while(true) { <- avoided loop
//            if (pos == left) {
//                break;
//            }
//            pos++;
//            parentFinder--;
//        }

        while (2 * k + childFinder <= right) { // if less than right, the element at k has no children (no need to swap) // (2 * k) + 1

            int j = 2 * k + childFinder; // find 1st child

            if (j < right && maxBinaryHeap[j] < maxBinaryHeap[j + 1]) // check if we should swap with left or right child
                j++;

            if (maxBinaryHeap[k] >= maxBinaryHeap[j]) // check for need to swap
                break;

            int tmp = maxBinaryHeap[k]; // swap if we didn't break the loop
            maxBinaryHeap[k] = maxBinaryHeap[j];
            maxBinaryHeap[j] = tmp;

            k = j; // repeat the procedure but with increased k
        }
    }

    public void sort() {

        int k; // parent
        int adder; // part that needs to be added to 2 * k in order to find the right parent.

        /*
        * Depending on the 1st position of our slice of array (- examples: pos(1), pos(2), pos(3), pos(4) or pos(N) -) ,
        * to find the parent, 2 * k needs to be summed with specific numbers. The pattern to find these numbers
        * repeats itself, however, to automatize, when 1st position is 1, 2, 3 or 4, we manually have to set the parent.
        * for the remaining starting positions we need to check if the position is even or uneven.
        * If the starting position is uneven, take 1st position of slice (left), subtract 1, floor divide it by 2 and multiply the result by 2.
        * If the starting position is even, take 1st position of slice(left), floor divide it by 2 and multiply the result by 2.
        * This avoids a loop.
        */
        if (left == 0 || left == 1) { // for these 2 starting positions, create parent manually
            k = right/2;
        } else if (left == 2 || left == 3) { // for these 2 starting positions, create parent manually
            k = (right / 2) + 2;
        } else { // for the following starting positions (to infinity) find if even or uneven and follow the procedure in the description above
            if (left % 2 == 1) {
                adder = ((left - 1) / 2) * 2;
            }
            else {
                adder = (left / 2) * 2;
            }
            k = (right/2) + adder;
//            for (int i = 2; i <= left; i+=2) { <- avoided loop
//                if (left == i || left == i+1) {
//                    k = (right/2) + i;
//                    break;
//                }
//            }
        }


        while (k >= left) { // ensure that we are dealing with a max (or min) heap
            _sink(k);
            k--; // move to the next parent
        }

        // In the next while loop we start removing elements from the start of the array and placing them at the end.
        // We then decrease the size, so the sink operation only works on the unsorted part of the array.
        // Eventually, we get the sorted array in ascending order.
        while (right >= left) {
            int tmp = maxBinaryHeap[left];
            maxBinaryHeap[left] = maxBinaryHeap[right];
            maxBinaryHeap[right] = tmp;
            right--;
            _sink(left);
        }
    }

    public static void main(String[] args) {

        // TESTING (Positions start at 0 and sorting happens in place)
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arrForSliceSort = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        // test the sorting of the complete array
        Heapsort heapsort = new Heapsort(arr);
        System.out.println("\nSorting full array: \n" + Arrays.toString(arr));
        heapsort.sort();
        System.out.println("Sorted: \n"+ Arrays.toString(arr));
        System.out.println("\n---------------------------\n");

        // test the partial sorting
        Heapsort heapsortSlice = new Heapsort(arrForSliceSort, 2, 7);
        System.out.println("Sorting from pos 2 to 7: \n" + Arrays.toString(arrForSliceSort));
        heapsortSlice.sort();
        System.out.println("Sorted: \n"+ Arrays.toString(arrForSliceSort) + "\n");

    }
}
