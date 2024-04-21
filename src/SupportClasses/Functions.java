package SupportClasses;

import java.util.ArrayList;

public class Functions {

    public double findAvg(ArrayList<Double> times) {
        double sum = 0;
        for (double t : times) {
            sum+=t;
        }
        return sum/times.size();
    }
}
