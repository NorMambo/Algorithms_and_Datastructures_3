package Task2;

import SupportClasses.CSVReaderWriter;
import SupportClasses.Functions;
import SupportClasses.Timer;
import Task1.QuadraticProbingHashTable;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {

        Functions fs = new Functions();
        Timer t = new Timer();

        ArrayList<Double> execTimes = new ArrayList<>();
        ArrayList<Double> collisionList = new ArrayList<>();
        ArrayList<Double> insertionWhen1stCollision = new ArrayList<>();
        ArrayList<Double> maxOffsets = new ArrayList<>();
        ArrayList<Double> nrInsertions = new ArrayList<>();

        ArrayList<Integer> tries = new ArrayList<>();

        CSVReaderWriter readerWriter = new CSVReaderWriter();

        int nrCars = 5000;

        for (int i = 0; i < 50; i++) {

            tries.add(i+1);
            ArrayList<Car> cars = new ArrayList<>();
            QuadraticProbingHashTable<Object> hashTable = new QuadraticProbingHashTable<>(10007); //41

            for (int j = 0; j < nrCars; j++)
                cars.add(new Car());

            double time = t.timeFunction(() -> {
                for (Car c : cars) {
                    try {
                        hashTable.insert(c);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                return null;
            });

            execTimes.add(time);
            collisionList.add((double)hashTable.getCollisions());
            insertionWhen1stCollision.add((double)hashTable.getFirstCollisionAtInsertion());
            nrInsertions.add((double)hashTable.getInsertions());
            maxOffsets.add(hashTable.getMaxOffset());
        }

        System.out.println("avg nr of collisions after 50 runs: " + fs.findAvg(collisionList));
        System.out.println("avg 1st collision insertion nr.: " + fs.findAvg(insertionWhen1stCollision));
        System.out.println("avg successful insertions: " + fs.findAvg(nrInsertions));

        readerWriter.writeToFile(collisionList,tries,"src/Task2/all_details_collisions.csv");
        readerWriter.writeToFile(insertionWhen1stCollision,tries,"src/Task2/all_details_1st_collision.csv");
        readerWriter.writeToFile(maxOffsets,tries,"src/Task2/all_details_max_offsets.csv");
        readerWriter.writeToFile(execTimes,tries,"src/Task2/all_details_runtime.csv");

        readerWriter.writeToFile(collisionList,tries,"src/Task2/partial_details_collisions.csv");
        readerWriter.writeToFile(insertionWhen1stCollision,tries,"src/Task2/partial_details_1st_collision.csv");
        readerWriter.writeToFile(maxOffsets,tries,"src/Task2/partial_details_max_offsets.csv");
        readerWriter.writeToFile(execTimes,tries,"src/Task2/partial_details_runtime.csv");
    }
}
