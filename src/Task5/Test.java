package Task5;

import SupportClasses.CSVReaderWriter;
import SupportClasses.Functions;
import SupportClasses.Timer;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {

        Functions fs = new Functions();
        Timer t = new Timer();
        int l = -100000;
        int h = 100000;

        int arrLength = 50000;
        int nrOfLimitsToTest = 30;

        ArrayList<int[]> allArrays = new ArrayList<>();
        int[] originalArr = new int[arrLength];

        // CREATE ARRAY OF RANDOM INTEGERS
        for (int j = 0; j < originalArr.length; j++) {
            int random = (int) Math.floor(Math.random() * ((h - l) + 1) + l);
            originalArr[j] = random;
        }

        // CREATE COPIES OF THE ARRAY INSTANCE BECAUSE SORT MODIFIES THE ORIGINAL ARRAY (EX: if 30 limits to test, create 60 copies of the array)
        for (int i = 0; i < nrOfLimitsToTest*3; i++) {
            int[] copy = new int[originalArr.length];
            System.arraycopy(originalArr, 0, copy, 0, originalArr.length);
            allArrays.add(copy);
        }

        ArrayList<Double> insertTimes = new ArrayList<>();
        ArrayList<Double> heapTimes = new ArrayList<>();
        ArrayList<Double> pureTimes = new ArrayList<>();
        ArrayList<Integer> limitSizes = new ArrayList<>();
        for (int i = 0; i <= nrOfLimitsToTest; i++) { // add limits from 0 to nrOfLimitsToTest
            limitSizes.add(i);
        }


        int counter = 0;
        // TIME EXECUTION
        for (int i = 0; i < allArrays.size(); i+=3) {

            QuickSortInsertsort QSinsert = new QuickSortInsertsort();
            QuickSortHeapsort QSheap = new QuickSortHeapsort();
            QuickSortPure QSpure = new QuickSortPure();

            int[] intArr1 = allArrays.get(i);
            int[] intArr2 = allArrays.get(i+1);
            int[] intArr3 = allArrays.get(i+2);

            int j = counter;
            counter++;

            int limit = limitSizes.get(j);
            System.out.println("LIMIT: " + limit);

            // TIME QuickSortInsertsort
            double QSinsertTime = t.timeFunction(() -> {
                QSinsert.sort(intArr1, limit);
                return null;
            });
            System.out.println("QS-INSERT TIME: " + QSinsertTime);
            insertTimes.add(QSinsertTime);

            // TIME QuickSortHeapsort
            double QSheapTime = t.timeFunction(() -> {
                QSheap.sort(intArr2, limit);
                return null;
            });
            System.out.println("QS-HEAP TIME: " + QSheapTime);
            heapTimes.add(QSheapTime);

            // TIME QuickSortHeapsort
            double QSpureTime = t.timeFunction(() -> {
                QSpure.sort(intArr3);
                return null;
            });
            System.out.println("QS-PURE TIME: " + QSpureTime + "\n\n");
            pureTimes.add(QSpureTime);

        }
        System.out.println("PURE QUICK-SORT AVG TIME: " + fs.findAvg(pureTimes));

        CSVReaderWriter readerWriter = new CSVReaderWriter();
        readerWriter.writeToFile(insertTimes, limitSizes, "src/Task5/insert.csv");
        readerWriter.writeToFile(heapTimes, limitSizes, "src/Task5/heap.csv");
        readerWriter.writeToFile(pureTimes, limitSizes, "src/Task5/pure.csv");
    }
}
