package Task7;

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
        ShellSort ss = new ShellSort();

        int high = 100000;
        int low = -100000;

        int[] arrSizes = {100000, 200000, 300000};

        // TEST
        for (int size : arrSizes) {

            ArrayList<Double> timesShell = new ArrayList<>();
            ArrayList<Double> timesCiura = new ArrayList<>();
            ArrayList<Double> timesKnuth = new ArrayList<>();
            ArrayList<Integer> lengths = new ArrayList<>();

            for (int i = 1000; i <= size; i += 1000) {

                int[] arr1 = new int[i];
                int[] arr2 = new int[i];
                int[] arr3 = new int[i];

                for (int j = 0; j < arr1.length; j++) {

                    int randInt = (int) Math.floor(Math.random() * (high - low) + low);
                    arr1[j] = randInt;
                    arr2[j] = randInt;
                    arr3[j] = randInt;
                }

                double shellTime = t.timeFunction(() -> {
                    ss.sortWithShell(arr1);
                    return null;
                });

                double ciuraTime = t.timeFunction(() -> {
                    ss.sortWithCiura(arr2);
                    return null;
                });

                double knuthTime = t.timeFunction(() -> {
                    ss.sortWithKnuth(arr3);
                    return null;
                });

                lengths.add(arr1.length);
                timesShell.add(shellTime);
                timesCiura.add(ciuraTime);
                timesKnuth.add(knuthTime);
            }

            System.out.println("AVG time Shell " + size + ": - " + fs.findAvg(timesShell));
            System.out.println("AVG time Ciura " + size + ": - " + fs.findAvg(timesCiura));
            System.out.println("AVG time Knuth " + size + ": - " + fs.findAvg(timesKnuth) + "\n");

            readerWriter.writeToFile(timesShell, lengths, "src/Task7/shell_" + size + ".csv");
            readerWriter.writeToFile(timesCiura, lengths, "src/Task7/ciura_" + size + ".csv");
            readerWriter.writeToFile(timesKnuth, lengths, "src/Task7/knuth_" + size + ".csv");

        }
    }
}
