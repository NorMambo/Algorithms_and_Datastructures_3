package SupportClasses;

import java.util.function.Supplier;

interface timeIt {
    <T> double timeFunction(Supplier <T> function);
}
public class Timer implements timeIt{

    public Timer () {}

    @Override
    public <T> double timeFunction(Supplier<T> function) {
        double start = System.nanoTime();
        T result = function.get();
        double end = System.nanoTime();
        double executionTime = end - start;
        return executionTime/1000000d; // return time in ms
//        return executionTime; // return time in nanoseconds
    }
}
