package Task6;

import SupportClasses.CSVReaderWriter;
import SupportClasses.Functions;
import SupportClasses.Timer;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {

        CSVReaderWriter readerWriter = new CSVReaderWriter();
        Functions fs = new Functions();
        Timer t = new Timer();
        ArrayList<Double> timesIterative = new ArrayList<>();
        ArrayList<Double> timesRecursive = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();
        int high = 50000;
        int low = -50000;

        MergeSortIterative msi = new MergeSortIterative();
        MergeSortRecursive msr = new MergeSortRecursive();

        for (int i = 100; i <= 10000; i+=100) {

            int[] arr1 = new int[i];
            int[] arr2 = new int[i];

            for (int j = 0; j < arr1.length; j++) {

                int randInt = (int) Math.floor(Math.random()*(high - low) + low);
                arr1[j] = randInt;
                arr2[j] = randInt;
            }

            // time iterative
            double timeIter = t.timeFunction(() -> {
                msi.sort(arr1);
               return null;
            });

            // time recursive
            double timeRec = t.timeFunction(() -> {
                msr.sort(arr2);
                return null;
            });

            sizes.add(arr1.length);
            timesIterative.add(timeIter);
            timesRecursive.add(timeRec);
        }

        System.out.println("AVG time Iterative Mergesort: - " + fs.findAvg(timesIterative));
        System.out.println("AVG time Recursive Mergesort: - " + fs.findAvg(timesRecursive));

        readerWriter.writeToFile(timesIterative,sizes,"src/Task6/Merge_Iterative.csv");
        readerWriter.writeToFile(timesIterative,sizes,"src/Task6/Merge_Recursive.csv");

    }
}
